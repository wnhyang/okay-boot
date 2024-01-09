package cn.wnhyang.okay.admin.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.admin.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.admin.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.admin.enums.login.LoginResultEnum;
import cn.wnhyang.okay.admin.enums.login.LoginTypeEnum;
import cn.wnhyang.okay.admin.login.LoginUser;
import cn.wnhyang.okay.admin.redis.RedisKeyConstants;
import cn.wnhyang.okay.admin.vo.login.EmailLoginReqVO;
import cn.wnhyang.okay.admin.vo.login.LoginReqVO;
import cn.wnhyang.okay.admin.vo.login.LoginRespVO;
import cn.wnhyang.okay.admin.vo.login.RegisterReqVO;
import cn.wnhyang.okay.framework.common.enums.CommonStatusEnum;
import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.common.enums.UserTypeEnum;
import cn.wnhyang.okay.framework.common.util.RegexUtils;
import cn.wnhyang.okay.framework.common.util.ServletUtils;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static cn.wnhyang.okay.admin.enums.ErrorCodeConstants.*;
import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final LoginLogService loginLogService;

    private final LoginService loginService;

    private final ValueOperations<String, String> valueOperations;

    public LoginRespVO login(LoginReqVO reqVO) {
        String account = reqVO.getAccount();
        LoginUser user;
        LoginTypeEnum loginTypeEnum;
        if (StrUtil.isNotEmpty(account)) {
            if (ReUtil.isMatch(RegexUtils.MOBILE, account)) {
                user = userService.getUserInfo(account, account, "");
                loginTypeEnum = LoginTypeEnum.LOGIN_MOBILE;
            } else if (ReUtil.isMatch(RegexUtils.EMAIL, account)) {
                user = userService.getUserInfo(account, "", account);
                loginTypeEnum = LoginTypeEnum.LOGIN_EMAIL;
            } else {
                user = userService.getUserInfo(account, "", "");
                loginTypeEnum = LoginTypeEnum.LOGIN_USERNAME;
            }
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!BCrypt.checkpw(reqVO.getPassword(), user.getPassword())) {
            createLoginLog(user.getId(), account, loginTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), account, loginTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        loginService.login(user, DeviceTypeEnum.PC);
        createLoginLog(user.getId(), account, loginTypeEnum, LoginResultEnum.SUCCESS);
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setUserId(user.getId());
        loginRespVO.setToken(StpUtil.getTokenValue());
        loginRespVO.setRoles(user.getRoles());
        return loginRespVO;
    }

    public LoginRespVO login(EmailLoginReqVO reqVO) {
        String email = reqVO.getEmail();
        String code = reqVO.getCode();
        LoginUser user;
        LoginTypeEnum loginTypeEnum;
        if (StrUtil.isNotEmpty(email) && ReUtil.isMatch(RegexUtils.EMAIL, email)) {
            user = userService.getUserInfo("", "", email);
            loginTypeEnum = LoginTypeEnum.LOGIN_EMAIL_CODE;
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        String emailCode = valueOperations.get(RedisKeyConstants.EMAIL_CODE);
        if (!code.equals(emailCode)) {
            createLoginLog(user.getId(), email, loginTypeEnum, LoginResultEnum.BAD_EMAIL_CODE);
            throw exception(AUTH_LOGIN_BAD_EMAIL_CODE);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), email, loginTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        loginService.login(user, DeviceTypeEnum.PC);
        createLoginLog(user.getId(), email, loginTypeEnum, LoginResultEnum.SUCCESS);
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setUserId(user.getId());
        loginRespVO.setToken(StpUtil.getTokenValue());
        loginRespVO.setRoles(user.getRoles());
        return loginRespVO;
    }

    public void generateEmailCode(String account) {
    }

    public void logout() {
        LoginUser loginUser = (LoginUser) loginService.getLoginUser();
        if (loginUser != null) {
            StpUtil.logout();
            createLoginLog(loginUser.getId(), loginUser.getUsername(), LoginTypeEnum.LOGOUT_SELF, LoginResultEnum.SUCCESS);
        }
    }

    public void register(RegisterReqVO reqVO) {
        String username = reqVO.getUsername();
        String password = reqVO.getPassword();
        Integer userType = UserTypeEnum.valueOf(reqVO.getUserType()).getType();
        UserCreateReqDTO reqDTO = new UserCreateReqDTO();
        reqDTO.setUsername(username);
        reqDTO.setNickname(username);
        reqDTO.setPassword(BCrypt.hashpw(password));
        reqDTO.setType(userType);
        userService.registerUser(reqDTO);
    }

    private void createLoginLog(Long userId, String account, LoginTypeEnum loginTypeEnum, LoginResultEnum loginResultEnum) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLoginType(loginTypeEnum.getType());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(UserTypeEnum.PC.getType());
        reqDTO.setAccount(account);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResultEnum.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResultEnum.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }
}

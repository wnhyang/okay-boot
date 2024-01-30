package cn.wnhyang.okay.admin.service.impl;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.wnhyang.okay.admin.login.LoginUser;
import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.common.enums.UserTypeEnum;
import cn.wnhyang.okay.framework.web.core.service.LoginService;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public class LoginServiceImpl implements LoginService<LoginUser> {

    public static final String LOGIN_USER_KEY = "login_user";
    public static final String USER_KEY = "user_id";

    public void login(LoginUser loginUser) {
        login(loginUser, null);
    }

    @Override
    public void login(LoginUser loginUser, DeviceTypeEnum deviceEnum) {
        SaLoginModel model = new SaLoginModel();
        if (ObjectUtil.isNotNull(deviceEnum)) {
            model.setDevice(deviceEnum.getDevice());
        }
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        UserTypeEnum userType = UserTypeEnum.valueOf(loginUser.getType());
        if (userType == UserTypeEnum.PC) {
            model.setTimeout(1800).setActiveTimeout(600);
        } else if (userType == UserTypeEnum.APP) {
            model.setTimeout(86400).setActiveTimeout(1800);
        }
        StpUtil.login(loginUser.getId(), model);
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    @Override
    public LoginUser getLoginUser() {
        if (!StpUtil.isLogin()) {
            return null;
        }
        SaSession session = StpUtil.getTokenSession();
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        LoginUser loginUser = (LoginUser) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }
}

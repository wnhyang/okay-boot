package cn.wnhyang.okay.framework.security.core.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.enums.UserTypeEnum;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wnhyang
 * @date 2023/7/28
 **/
@Setter
public class StpInterfaceImpl implements StpInterface {

    /**
     * 登录服务
     */
    private LoginService<Login> loginService;

    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Login loginUser = loginService.getLoginUser();
        UserTypeEnum userType = UserTypeEnum.valueOf(loginUser.getType());
        if (userType == UserTypeEnum.PC) {
            return new ArrayList<>(loginUser.getPermissions());
        }

        return new ArrayList<>();
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Login loginUser = loginService.getLoginUser();
        UserTypeEnum userType = UserTypeEnum.valueOf(loginUser.getType());
        if (userType == UserTypeEnum.PC) {
            return new ArrayList<>(loginUser.getRoleValues());
        }

        return new ArrayList<>();
    }
}
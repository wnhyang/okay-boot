package cn.wnhyang.okay.web.service.impl;

import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import cn.wnhyang.okay.web.login.LoginUser;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public class LoginServiceImpl implements LoginService<LoginUser> {
    @Override
    public void login(LoginUser loginUser, DeviceTypeEnum deviceEnum) {

    }

    @Override
    public LoginUser getLoginUser() {
        return null;
    }
}

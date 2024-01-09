package cn.wnhyang.okay.admin.service.impl;

import cn.wnhyang.okay.admin.convert.loginlog.LoginLogConvert;
import cn.wnhyang.okay.admin.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.admin.entity.LoginLogDO;
import cn.wnhyang.okay.admin.mapper.LoginLogMapper;
import cn.wnhyang.okay.admin.service.LoginLogService;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogPageReqVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 登录日志
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        LoginLogDO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageResult<LoginLogDO> getLoginLogPage(LoginLogPageReqVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }
}

package cn.wnhyang.okay.admin.service.impl;

import cn.wnhyang.okay.admin.convert.LoginLogConvert;
import cn.wnhyang.okay.admin.dto.LoginLogCreateDTO;
import cn.wnhyang.okay.admin.entity.LoginLogPO;
import cn.wnhyang.okay.admin.mapper.LoginLogMapper;
import cn.wnhyang.okay.admin.service.LoginLogService;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogPageVO;
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
    public void createLoginLog(LoginLogCreateDTO reqDTO) {
        LoginLogPO loginLog = LoginLogConvert.INSTANCE.convert(reqDTO);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageResult<LoginLogPO> getLoginLogPage(LoginLogPageVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }
}

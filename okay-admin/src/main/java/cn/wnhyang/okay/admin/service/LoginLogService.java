package cn.wnhyang.okay.admin.service;


import cn.wnhyang.okay.admin.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.admin.entity.LoginLogDO;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogPageReqVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;

import javax.validation.Valid;

/**
 * 系统访问记录
 *
 * @author wnhyang
 * @since 2023/07/25
 */
public interface LoginLogService {

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(@Valid LoginLogCreateReqDTO reqDTO);

    /**
     * 分页查询登录日志
     *
     * @param reqVO 分页请求
     * @return 登录日志分页
     */
    PageResult<LoginLogDO> getLoginLogPage(LoginLogPageReqVO reqVO);
}

package cn.wnhyang.okay.admin.service;


import cn.wnhyang.okay.admin.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.admin.entity.OperateLogPO;
import cn.wnhyang.okay.admin.vo.operatelog.OperateLogPageVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;

/**
 * 操作日志记录
 *
 * @author wnhyang
 * @since 2023/06/05
 */
public interface OperateLogService {

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    void createOperateLog(OperateLogCreateDTO createReqDTO);

    /**
     * 分页查询操作日志
     *
     * @param reqVO 分页请求
     * @return 分页操作日志
     */
    PageResult<OperateLogPO> getOperateLogPage(OperateLogPageVO reqVO);
}

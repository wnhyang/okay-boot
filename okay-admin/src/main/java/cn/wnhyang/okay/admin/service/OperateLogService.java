package cn.wnhyang.okay.admin.service;


import cn.wnhyang.okay.admin.dto.operatelog.OperateLogCreateReqDTO;
import cn.wnhyang.okay.admin.entity.OperateLogDO;
import cn.wnhyang.okay.admin.vo.operatelog.OperateLogPageReqVO;
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
    void createOperateLog(OperateLogCreateReqDTO createReqDTO);

    /**
     * 分页查询操作日志
     *
     * @param reqVO 分页请求
     * @return 分页操作日志
     */
    PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO reqVO);
}

package cn.wnhyang.okay.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.admin.convert.OperateLogConvert;
import cn.wnhyang.okay.admin.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.admin.entity.OperateLogPO;
import cn.wnhyang.okay.admin.entity.UserPO;
import cn.wnhyang.okay.admin.mapper.OperateLogMapper;
import cn.wnhyang.okay.admin.service.OperateLogService;
import cn.wnhyang.okay.admin.service.UserService;
import cn.wnhyang.okay.admin.vo.operatelog.OperateLogPageVO;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.dto.LogCreateReqDTO;
import cn.wnhyang.okay.framework.log.core.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static cn.wnhyang.okay.admin.entity.OperateLogPO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static cn.wnhyang.okay.admin.entity.OperateLogPO.RESULT_MAX_LENGTH;
import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;
import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;

/**
 * 操作日志
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService, LogService {

    private final OperateLogMapper operateLogMapper;

    private final UserService userService;

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    @Override
    public void createOperateLog(OperateLogCreateDTO createReqDTO) {
        OperateLogPO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logDO.setJavaMethodArgs(StrUtil.subPre(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtil.subPre(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
    }

    @Override
    public PageResult<OperateLogPO> getOperateLogPage(OperateLogPageVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = convertSet(userService.getUserListByNickname(reqVO.getUserNickname()), UserPO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }

    @Override
    public CommonResult<Boolean> createLog(LogCreateReqDTO reqDTO) {
        OperateLogCreateDTO operateLog = OperateLogConvert.INSTANCE.convert(reqDTO);
        createOperateLog(operateLog);
        return success(true);
    }
}

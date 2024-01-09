package cn.wnhyang.okay.admin.convert.operatelog;


import cn.wnhyang.okay.admin.dto.operatelog.OperateLogCreateReqDTO;
import cn.wnhyang.okay.admin.entity.OperateLogDO;
import cn.wnhyang.okay.admin.vo.operatelog.OperateLogRespVO;
import cn.wnhyang.okay.framework.log.core.dto.LogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/6/6
 **/
@Mapper
public interface OperateLogConvert {
    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    OperateLogDO convert(OperateLogCreateReqDTO reqDTO);

    OperateLogRespVO convert(OperateLogDO operateLog);

    OperateLogCreateReqDTO convert(LogCreateReqDTO reqDTO);
}

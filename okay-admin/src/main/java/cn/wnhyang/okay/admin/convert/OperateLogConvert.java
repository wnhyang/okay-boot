package cn.wnhyang.okay.admin.convert;


import cn.wnhyang.okay.admin.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.admin.entity.OperateLogPO;
import cn.wnhyang.okay.admin.vo.operatelog.OperateLogVO;
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

    OperateLogPO convert(OperateLogCreateDTO reqDTO);

    OperateLogVO convert(OperateLogPO operateLog);

    OperateLogCreateDTO convert(LogCreateReqDTO reqDTO);
}

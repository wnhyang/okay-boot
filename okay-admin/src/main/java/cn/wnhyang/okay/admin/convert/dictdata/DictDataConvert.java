package cn.wnhyang.okay.admin.convert.dictdata;


import cn.wnhyang.okay.admin.dto.dict.DictDataRespDTO;
import cn.wnhyang.okay.admin.entity.DictDataDO;
import cn.wnhyang.okay.admin.vo.dictdata.DictDataCreateReqVO;
import cn.wnhyang.okay.admin.vo.dictdata.DictDataRespVO;
import cn.wnhyang.okay.admin.vo.dictdata.DictDataSimpleRespVO;
import cn.wnhyang.okay.admin.vo.dictdata.DictDataUpdateReqVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataDO convert(DictDataCreateReqVO reqVO);

    DictDataRespVO convert(DictDataDO bean);

    DictDataDO convert(DictDataUpdateReqVO reqVO);

    List<DictDataSimpleRespVO> convertList(List<DictDataDO> list);

    PageResult<DictDataRespVO> convertPage(PageResult<DictDataDO> page);

    DictDataRespDTO convert02(DictDataDO dictData);
}

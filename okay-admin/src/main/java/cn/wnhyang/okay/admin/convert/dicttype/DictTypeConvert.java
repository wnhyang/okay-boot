package cn.wnhyang.okay.admin.convert.dicttype;

import cn.wnhyang.okay.admin.entity.DictTypeDO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeCreateReqVO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeRespVO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeSimpleRespVO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeUpdateReqVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/9/13
 **/
@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    DictTypeDO convert(DictTypeCreateReqVO reqVO);

    DictTypeDO convert(DictTypeUpdateReqVO reqVO);

    PageResult<DictTypeRespVO> convertPage(PageResult<DictTypeDO> dictTypePage);

    DictTypeRespVO convert(DictTypeDO dictType);

    List<DictTypeSimpleRespVO> convertList(List<DictTypeDO> list);
}

package cn.wnhyang.okay.admin.convert;

import cn.wnhyang.okay.admin.entity.DictTypePO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeCreateVO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeRespVO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeSimpleVO;
import cn.wnhyang.okay.admin.vo.dicttype.DictTypeUpdateVO;
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

    DictTypePO convert(DictTypeCreateVO reqVO);

    DictTypePO convert(DictTypeUpdateVO reqVO);

    PageResult<DictTypeRespVO> convertPage(PageResult<DictTypePO> dictTypePage);

    DictTypeRespVO convert(DictTypePO dictType);

    List<DictTypeSimpleVO> convertList(List<DictTypePO> list);
}

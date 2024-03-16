package cn.wnhyang.okay.admin.convert;


import cn.wnhyang.okay.admin.entity.RsaPO;
import cn.wnhyang.okay.admin.vo.rsa.RsaCreateVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaRespVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaUpdateVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Mapper
public interface RsaConvert {
    RsaConvert INSTANCE = Mappers.getMapper(RsaConvert.class);

    RsaPO convert(RsaCreateVO reqVO);

    RsaPO convert(RsaUpdateVO reqVO);

    PageResult<RsaRespVO> convertPage(PageResult<RsaPO> page);

    RsaRespVO convert(RsaPO rsa);
}

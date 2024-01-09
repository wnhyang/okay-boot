package cn.wnhyang.okay.admin.convert.rsa;


import cn.wnhyang.okay.admin.entity.RsaDO;
import cn.wnhyang.okay.admin.vo.rsa.RsaCreateReqVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaRespVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaUpdateReqVO;
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

    RsaDO convert(RsaCreateReqVO reqVO);

    RsaDO convert(RsaUpdateReqVO reqVO);

    PageResult<RsaRespVO> convertPage(PageResult<RsaDO> page);

    RsaRespVO convert(RsaDO rsa);
}

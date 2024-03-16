package cn.wnhyang.okay.admin.convert;


import cn.wnhyang.okay.admin.dto.LoginLogCreateDTO;
import cn.wnhyang.okay.admin.entity.LoginLogPO;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/7/25
 **/
@Mapper
public interface LoginLogConvert {
    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    LoginLogPO convert(LoginLogCreateDTO reqDTO);

    PageResult<LoginLogVO> convertPage(PageResult<LoginLogPO> page);
}

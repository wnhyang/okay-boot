package cn.wnhyang.okay.admin.convert.loginlog;


import cn.wnhyang.okay.admin.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.admin.entity.LoginLogDO;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogRespVO;
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

    LoginLogDO convert(LoginLogCreateReqDTO reqDTO);

    PageResult<LoginLogRespVO> convertPage(PageResult<LoginLogDO> page);
}

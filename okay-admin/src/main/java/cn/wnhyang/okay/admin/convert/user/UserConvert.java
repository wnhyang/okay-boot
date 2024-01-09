package cn.wnhyang.okay.admin.convert.user;


import cn.wnhyang.okay.admin.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.admin.entity.UserDO;
import cn.wnhyang.okay.admin.login.LoginUser;
import cn.wnhyang.okay.admin.vo.user.UserCreateReqVO;
import cn.wnhyang.okay.admin.vo.user.UserInfoRespVO;
import cn.wnhyang.okay.admin.vo.user.UserRespVO;
import cn.wnhyang.okay.admin.vo.user.UserUpdateReqVO;
import cn.wnhyang.okay.admin.vo.userprofile.UserProfileRespVO;
import cn.wnhyang.okay.admin.vo.userprofile.UserProfileUpdateReqVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespVO convert02(UserDO userDO);

    LoginUser convert(UserDO userDO);

    UserDO convert(UserCreateReqDTO reqDTO);

    UserDO convert(UserCreateReqVO reqVO);

    UserDO convert(UserUpdateReqVO reqVO);

    PageResult<UserRespVO> convert(PageResult<UserDO> pageResult);

    UserInfoRespVO.UserVO convert03(UserDO user);

    UserProfileRespVO convert04(UserDO user);

    UserDO convert(UserProfileUpdateReqVO reqVO);
}

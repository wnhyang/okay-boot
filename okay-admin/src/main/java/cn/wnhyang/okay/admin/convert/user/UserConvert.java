package cn.wnhyang.okay.admin.convert.user;


import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.admin.convert.role.RoleConvert;
import cn.wnhyang.okay.admin.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.admin.entity.RoleDO;
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

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespVO convert02(UserDO userDO);

    default UserRespVO convert(UserDO userDO, List<RoleDO> roleDOList) {
        UserRespVO userRespVO = convert02(userDO);
        if (CollUtil.isNotEmpty(roleDOList)) {
            userRespVO.setRoles(RoleConvert.INSTANCE.convert02(roleDOList));
        }
        return userRespVO;
    }

    LoginUser convert(UserDO userDO);

    UserDO convert(UserCreateReqDTO reqDTO);

    UserDO convert(UserCreateReqVO reqVO);

    UserDO convert(UserUpdateReqVO reqVO);

    PageResult<UserRespVO> convert(PageResult<UserDO> pageResult);

    UserInfoRespVO.UserVO convert03(UserDO user);

    UserProfileRespVO convert04(UserDO user);

    UserDO convert(UserProfileUpdateReqVO reqVO);
}

package cn.wnhyang.okay.admin.convert;


import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.admin.dto.UserCreateDTO;
import cn.wnhyang.okay.admin.entity.RolePO;
import cn.wnhyang.okay.admin.entity.UserPO;
import cn.wnhyang.okay.admin.login.LoginUser;
import cn.wnhyang.okay.admin.vo.user.UserCreateVO;
import cn.wnhyang.okay.admin.vo.user.UserInfoVO;
import cn.wnhyang.okay.admin.vo.user.UserRespVO;
import cn.wnhyang.okay.admin.vo.user.UserUpdateVO;
import cn.wnhyang.okay.admin.vo.userprofile.UserProfileUpdateVO;
import cn.wnhyang.okay.admin.vo.userprofile.UserProfileVO;
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

    UserRespVO convert02(UserPO userDO);

    default UserRespVO convert(UserPO userDO, List<RolePO> roleDOList) {
        UserRespVO userRespVO = convert02(userDO);
        if (CollUtil.isNotEmpty(roleDOList)) {
            userRespVO.setRoles(RoleConvert.INSTANCE.convert02(roleDOList));
        }
        return userRespVO;
    }

    LoginUser convert(UserPO userDO);

    UserPO convert(UserCreateDTO reqDTO);

    UserPO convert(UserCreateVO reqVO);

    UserPO convert(UserUpdateVO reqVO);

    PageResult<UserRespVO> convert(PageResult<UserPO> pageResult);

    UserInfoVO.UserVO convert03(UserPO user);

    UserProfileVO convert04(UserPO user);

    UserPO convert(UserProfileUpdateVO reqVO);
}

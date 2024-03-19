package cn.wnhyang.okay.system.convert;


import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.UserCreateDTO;
import cn.wnhyang.okay.system.entity.RolePO;
import cn.wnhyang.okay.system.entity.UserPO;
import cn.wnhyang.okay.system.login.LoginUser;
import cn.wnhyang.okay.system.vo.user.UserCreateVO;
import cn.wnhyang.okay.system.vo.user.UserInfoVO;
import cn.wnhyang.okay.system.vo.user.UserRespVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdateVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileVO;
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

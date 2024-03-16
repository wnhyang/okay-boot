package cn.wnhyang.okay.admin.convert;


import cn.wnhyang.okay.admin.dto.RoleSimpleVO;
import cn.wnhyang.okay.admin.entity.RolePO;
import cn.wnhyang.okay.admin.vo.role.RoleCreateVO;
import cn.wnhyang.okay.admin.vo.role.RoleRespVO;
import cn.wnhyang.okay.admin.vo.role.RoleUpdateVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Mapper
public interface RoleConvert {
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RolePO convert(RoleCreateVO reqVO);

    RolePO convert(RoleUpdateVO reqVO);

    RoleRespVO convert(RolePO role);

    PageResult<RoleRespVO> convert(PageResult<RolePO> pageResult);

    List<RoleSimpleVO> convert02(List<RolePO> roleList);
}

package cn.wnhyang.okay.admin.mapper;


import cn.wnhyang.okay.admin.entity.RoleMenuPO;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 角色和菜单关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuPO> {

    default List<RoleMenuPO> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(RoleMenuPO::getRoleId, roleIds);
    }

    default void deleteByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<RoleMenuPO>().eq(RoleMenuPO::getRoleId, roleId));
    }

    default Long selectCountByMenuId(Long menuId) {
        return selectCount(RoleMenuPO::getMenuId, menuId);
    }

    default List<RoleMenuPO> selectListByRoleId(Long roleId) {
        return selectList(RoleMenuPO::getRoleId, roleId);
    }

    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        delete(new LambdaQueryWrapper<RoleMenuPO>()
                .eq(RoleMenuPO::getRoleId, roleId)
                .in(RoleMenuPO::getMenuId, menuIds));
    }
}

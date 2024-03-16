package cn.wnhyang.okay.admin.mapper;


import cn.wnhyang.okay.admin.entity.MenuPO;
import cn.wnhyang.okay.admin.vo.menu.MenuListVO;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface MenuMapper extends BaseMapperX<MenuPO> {

    default MenuPO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(MenuPO::getParentId, parentId, MenuPO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(MenuPO::getParentId, parentId);
    }

    default List<MenuPO> selectList(MenuListVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MenuPO>()
                .likeIfPresent(MenuPO::getTitle, reqVO.getTitle())
                .eqIfPresent(MenuPO::getStatus, reqVO.getStatus()));
    }
}

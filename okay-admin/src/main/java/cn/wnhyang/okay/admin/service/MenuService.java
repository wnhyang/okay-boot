package cn.wnhyang.okay.admin.service;


import cn.wnhyang.okay.admin.entity.MenuPO;
import cn.wnhyang.okay.admin.vo.menu.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
public interface MenuService {

    /**
     * 创建菜单
     *
     * @param reqVO 菜单信息
     * @return 菜单id
     */
    Long createMenu(MenuCreateVO reqVO);

    /**
     * 更新菜单
     *
     * @param reqVO 菜单信息
     */
    void updateMenu(MenuUpdateVO reqVO);

    /**
     * 删除菜单
     *
     * @param id 菜单id
     */
    void deleteMenu(Long id);

    /**
     * 查询菜单列表
     *
     * @param reqVO 菜单
     * @return 菜单列表
     */
    List<MenuPO> getMenuList(MenuListVO reqVO);

    /**
     * 查询菜单
     *
     * @param id 菜单id
     * @return 菜单
     */
    MenuPO getMenu(Long id);

    /**
     * 根据菜单ids查询菜单
     *
     * @param ids 菜单ids
     * @return 菜单
     */
    List<MenuPO> getMenuList(Set<Long> ids);

    /**
     * 获取所有菜单列表
     *
     * @return 菜单列表
     */
    List<MenuPO> getMenuList();

    /**
     * 获取树形菜单列表
     *
     * @param reqVO 树形菜单请求
     * @return 树形菜单列表
     */
    List<MenuTreeRespVO> getMenuTreeList(MenuListVO reqVO);

    /**
     * 获取简单树形菜单不带根节点
     *
     * @return 树形菜单
     */
    List<MenuSimpleTreeVO> getMenuSimpleTreeList();

    /**
     * 获取简单树形菜单带根节点
     *
     * @return 树形菜单
     */
    List<MenuSimpleTreeVO> getMenuSimpleTreeListA();
}

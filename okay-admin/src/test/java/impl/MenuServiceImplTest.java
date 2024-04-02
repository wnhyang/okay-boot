package impl;

import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.admin.AdminApplication;
import cn.wnhyang.okay.system.convert.MenuConvert;
import cn.wnhyang.okay.system.entity.MenuPO;
import cn.wnhyang.okay.system.enums.permission.MenuType;
import cn.wnhyang.okay.system.mapper.MenuMapper;
import cn.wnhyang.okay.system.vo.menu.MenuSimpleTreeVO;
import cn.wnhyang.okay.system.vo.menu.MenuTreeRespVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static cn.wnhyang.okay.system.entity.MenuPO.ID_ROOT;

@SpringBootTest(classes = AdminApplication.class)
@Slf4j
class MenuServiceImplTest {

    @Resource
    private MenuMapper menuMapper;

    @Test
    void getMenuList() {
        List<MenuPO> menuPOList = menuMapper.selectList();
        List<Long> menuIds = new ArrayList<>();
        menuIds.add(105L);
        menuIds.add(1042L);
        Set<MenuPO> menuPOSet = findMenusWithParentsAndChildrenByIds(menuPOList, menuIds);
        for (MenuPO menu : menuPOSet) {
            log.info("id:{}, title:{}, parentId:{}", menu.getId(), menu.getTitle(), menu.getParentId());
        }
    }

    public Set<MenuPO> findMenusWithParentsAndChildrenByIds(List<MenuPO> menus, List<Long> menuIds) {
        Map<Long, MenuPO> menuMap = new HashMap<>();
        for (MenuPO menu : menus) {
            menuMap.put(menu.getId(), menu);
        }

        Set<MenuPO> result = new LinkedHashSet<>();// 使用LinkedHashSet保持插入顺序
        Set<Long> processedIds = new HashSet<>(); // 存储已处理过的菜单ID
        for (Long menuId : menuIds) {
            collectMenuParents(result, menuMap, menuId, processedIds);
            collectMenuChildren(result, menuMap, menuId);
        }

        return result;
    }

    private void collectMenuParents(Set<MenuPO> resultSet, Map<Long, MenuPO> menuMap, Long menuId, Set<Long> processedIds) {
        if (processedIds.contains(menuId)) {
            return; // 如果已经处理过此菜单，则不再处理
        }

        processedIds.add(menuId);
        MenuPO menu = menuMap.get(menuId);
        if (menu != null) {
            resultSet.add(menu);

            // 如果当前菜单不是根节点（即parentId不为0），继续查找其父菜单
            if (menu.getParentId() != 0L && !processedIds.contains(menu.getParentId())) {
                collectMenuParents(resultSet, menuMap, menu.getParentId(), processedIds);
            }
        }
    }

    private void collectMenuChildren(Set<MenuPO> resultSet, Map<Long, MenuPO> menuMap, Long menuId) {
        MenuPO menu = menuMap.get(menuId);
        if (menu != null) {
            resultSet.add(menu);

            // 添加当前菜单的所有子菜单
            for (MenuPO child : menuMap.values()) {
                if (child.getParentId().equals(menu.getId())) {
                    collectMenuChildren(resultSet, menuMap, child.getId());
                }
            }
        }
    }

    @Test
    void getMenuSimpleTreeList() {
        List<MenuPO> menuPOList = menuMapper.selectList();
        List<MenuSimpleTreeVO> menuSimpleTreeVOS = buildMenuTree(menuPOList, false);
        printMenuTree(menuSimpleTreeVOS);
    }

    public void printMenuTree(List<MenuSimpleTreeVO> menuList) {
        menuList.forEach(menu -> {
            log.info("id:{}, title:{}, parentId:{}", menu.getId(), menu.getTitle(), menu.getParentId());
            if (CollUtil.isNotEmpty(menu.getChildren())) {
                printMenuTree(menu.getChildren());
            }
        });
    }

    public List<MenuSimpleTreeVO> buildMenuTree(List<MenuPO> menuList, boolean removeButton) {

        List<MenuSimpleTreeVO> convert = MenuConvert.INSTANCE.convert02(menuList);

        if (removeButton) {
            // 移除按钮
            menuList.removeIf(menu -> menu.getType().equals(MenuType.BUTTON.getType()));
        }

        Map<Long, MenuSimpleTreeVO> menuTreeMap = new HashMap<>();
        for (MenuSimpleTreeVO menu : convert) {
            menuTreeMap.put(menu.getId(), menu);
        }

        menuTreeMap.values().stream().filter(menu -> !ID_ROOT.equals(menu.getParentId())).forEach(childMenu -> {
                    MenuSimpleTreeVO parentMenu = menuTreeMap.get(childMenu.getParentId());
                    if (parentMenu == null) {
                        log.info("id:{} 找不到父菜单 parentId:{}", childMenu.getId(), childMenu.getParentId());
                        return;
                    }
                    // 将自己添加到父节点中
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(childMenu);
                }

        );

        return menuTreeMap.values().stream().filter(menu -> ID_ROOT.equals(menu.getParentId())).collect(Collectors.toList());
    }

//    public List<MenuTreeRespVO> getMenuTreeList(MenuListVO reqVO) {
//
//        // 1、查询所有菜单
//        List<MenuPO> all = menuMapper.selectList();
//
//        // 1、查询满足条件的菜单
//        List<MenuPO> menus = menuMapper.selectList(reqVO);
//
//        Set<Long> menuIds = menus.stream().map(MenuPO::getId).collect(Collectors.toSet());
//
//        // 2、查询满足条件的的菜单的所有父菜单、父祖菜单的id set，直到父菜单的parentId等于0
//        Set<Long> parentIds = getParentIds(menus, all);
//
//        Set<Long> childrenIds = getChildrenIds(menus, all);
//
//        menuIds.addAll(parentIds);
//        menuIds.addAll(childrenIds);
//
//        List<MenuTreeRespVO> allMenus = all.stream().filter(menu -> menuIds.contains(menu.getId()))
//                .map(MenuConvert.INSTANCE::convert2MenuTreeRespVO).collect(Collectors.toList());
//
//        // 2、形成树形结合
//        return allMenus.stream().filter((menu) ->
//                menu.getParentId().equals(ID_ROOT)
//        ).peek((menu) ->
//                menu.setChildren(getChildren01(menu.getId(), allMenus))
//        ).sorted(
//                Comparator.comparingInt(MenuTreeRespVO::getOrderNo)
//        ).collect(Collectors.toList());
//    }

    private Set<Long> getChildrenIds(List<MenuPO> menus, List<MenuPO> all) {
        // 获取menus在all中的子菜单id集合
        return menus.stream().map(MenuPO::getId).map(id ->
                all.stream().filter(menu ->
                        menu.getParentId().equals(id)
                ).map(MenuPO::getId).collect(Collectors.toSet())
        ).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * 获取所有父菜单的id
     */
    private Set<Long> getParentIds(List<MenuPO> menus, List<MenuPO> all) {

        // 当menus的parentId不为0，取parentId的set集合
        Set<Long> parentIds = menus.stream().map(MenuPO::getParentId).filter(parentId ->
                !parentId.equals(ID_ROOT)
        ).collect(Collectors.toSet());

        if (parentIds.isEmpty()) {
            return Collections.emptySet();
        }

        Set<Long> result = new HashSet<>(parentIds);

        // 从所有菜单all中查询id等于parentIds的set集合中任意的菜单，直到顶级菜单，顶级菜单的parentId等于0
        for (MenuPO menu : all) {
            if (parentIds.contains(menu.getId())) {
                result.addAll(getParentIds(Collections.singletonList(menu), all));
            }
        }
        return result;
    }

    private List<MenuTreeRespVO> getChildren01(Long parentId, List<MenuTreeRespVO> all) {
        return all.stream().filter((menu ->
                menu.getParentId().equals(parentId))
        ).peek((menu ->
                menu.setChildren(getChildren01(menu.getId(), all)))
        ).sorted(
                Comparator.comparingInt(MenuTreeRespVO::getOrderNo)
        ).collect(Collectors.toList());
    }


}
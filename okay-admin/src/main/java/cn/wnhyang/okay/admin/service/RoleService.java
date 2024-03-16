package cn.wnhyang.okay.admin.service;


import cn.wnhyang.okay.admin.entity.RolePO;
import cn.wnhyang.okay.admin.vo.role.RoleCreateVO;
import cn.wnhyang.okay.admin.vo.role.RolePageVO;
import cn.wnhyang.okay.admin.vo.role.RoleUpdateVO;
import cn.wnhyang.okay.framework.common.enums.CommonStatusEnum;
import cn.wnhyang.okay.framework.common.pojo.PageResult;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
public interface RoleService {

    /**
     * 获得角色列表
     *
     * @param ids 角色编号数组
     * @return 角色列表
     */
    List<RolePO> getRoleList(Collection<Long> ids);

    /**
     * 获取角色编码
     *
     * @param ids 角色编号数组
     * @return 角色编码
     */
    default Set<String> getRoleValueList(Set<Long> ids) {
        return convertSet(getRoleList(ids), RolePO::getValue);
    }

    /**
     * 创建角色
     *
     * @param reqVO 角色信息
     * @return 角色
     */
    Long createRole(RoleCreateVO reqVO);

    /**
     * 更新角色
     *
     * @param reqVO 角色信息
     */
    void updateRole(RoleUpdateVO reqVO);

    /**
     * 更新用户状态
     *
     * @param id     id
     * @param status 状态
     */
    void updateRoleStatus(Long id, Integer status);

    /**
     * 删除角色
     *
     * @param id id
     */
    void deleteRole(Long id);

    /**
     * 查询角色
     *
     * @param id id
     * @return 角色
     */
    RolePO getRole(Long id);

    /**
     * 查询角色列表
     *
     * @param reqVO 请求
     * @return 角色列表
     */
    PageResult<RolePO> getRolePage(RolePageVO reqVO);

    /**
     * 获取角色列表
     *
     * @param status 状态
     * @return 角色列表
     */
    List<RolePO> getRoleList(CommonStatusEnum status);
}

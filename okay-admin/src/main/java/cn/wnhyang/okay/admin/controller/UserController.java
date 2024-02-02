package cn.wnhyang.okay.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.admin.convert.menu.MenuConvert;
import cn.wnhyang.okay.admin.convert.user.UserConvert;
import cn.wnhyang.okay.admin.entity.MenuDO;
import cn.wnhyang.okay.admin.entity.UserDO;
import cn.wnhyang.okay.admin.service.MenuService;
import cn.wnhyang.okay.admin.service.PermissionService;
import cn.wnhyang.okay.admin.service.UserService;
import cn.wnhyang.okay.admin.vo.user.*;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 用户
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MenuService menuService;

    private final PermissionService permissionService;

    private final LoginService loginService;

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return 用户id
     */
    @PostMapping("/create")
    @OperateLog(module = "后台-用户", name = "创建用户")
    @SaCheckPermission("system:user:create")
    public CommonResult<Long> createUser(@Valid @RequestBody UserCreateReqVO reqVO) {
        Long id = userService.createUser(reqVO);
        return success(id);
    }

    /**
     * 修改用户信息
     *
     * @param reqVO 用户信息
     * @return 结果
     */
    @PutMapping("/update")
    @OperateLog(module = "后台-用户", name = "修改用户信息")
    @SaCheckPermission("system:user:update")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserUpdateReqVO reqVO) {
        userService.updateUser(reqVO);
        return success(true);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/delete")
    @OperateLog(module = "后台-用户", name = "删除用户")
    @SaCheckPermission("system:user:delete")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }

    /**
     * 更新用户密码
     *
     * @param reqVO id+密码
     * @return 结果
     */
    @PutMapping("/updatePassword")
    @OperateLog(module = "后台-用户", name = "更新用户密码")
    @SaCheckPermission("system:user:updatePassword")
    public CommonResult<Boolean> updateUserPassword(@Valid @RequestBody UserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(reqVO);
        return success(true);
    }

    /**
     * 更新用户状态
     *
     * @param reqVO id+状态
     * @return 结果
     */
    @PutMapping("/updateStatus")
    @OperateLog(module = "后台-用户", name = "更新用户状态")
    @SaCheckPermission("system:user:update")
    public CommonResult<Boolean> updateUserStatus(@Valid @RequestBody UserUpdateStatusReqVO reqVO) {
        userService.updateUserStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * 查询用户信息
     *
     * @param id id
     * @return 用户
     */
    @GetMapping("/get")
    @OperateLog(module = "后台-用户", name = "查询用户")
    @SaCheckPermission("system:user:query")
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        UserDO user = userService.getUserById(id);
        return success(UserConvert.INSTANCE.convert02(user));
    }

    /**
     * 查询用户信息列表
     *
     * @param reqVO 查询条件
     * @return 用户列表
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-用户", name = "查询用户列表")
    @SaCheckPermission("system:user:list")
    public CommonResult<PageResult<UserRespVO>> getUserPage(@Valid UserPageReqVO reqVO) {
        PageResult<UserDO> pageResult = userService.getUserPage(reqVO);
        return success(UserConvert.INSTANCE.convert(pageResult));
    }

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    @OperateLog(module = "后台-用户", name = "查询用户信息")
    @SaCheckLogin
    public CommonResult<UserInfoRespVO> getUserInfo() {
        Login loginUser = loginService.getLoginUser();

        if (loginUser == null) {
            return CommonResult.error(UNAUTHORIZED);
        }
        Long id = loginUser.getId();

        UserDO user = userService.getUserById(id);
        UserInfoRespVO respVO = new UserInfoRespVO();
        UserInfoRespVO.UserVO userVO = UserConvert.INSTANCE.convert03(user);
        respVO.setUser(userVO);
        respVO.setRoles(loginUser.getRoleValues());
        respVO.setPermissions(loginUser.getPermissions());

        List<MenuDO> menus;
        if (loginService.isAdministrator(id)) {
            menus = menuService.getMenuList();
        } else {
            Set<Long> menuIds = permissionService.getRoleMenuListByRoleId(loginUser.getRoleIds());
            menus = menuService.getMenuList(menuIds);
        }
        respVO.setMenus(MenuConvert.INSTANCE.buildMenuTree(menus));
        return success(respVO);
    }
}

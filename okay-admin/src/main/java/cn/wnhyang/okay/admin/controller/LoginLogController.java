package cn.wnhyang.okay.admin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.admin.convert.loginlog.LoginLogConvert;
import cn.wnhyang.okay.admin.entity.LoginLogDO;
import cn.wnhyang.okay.admin.service.LoginLogService;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogPageReqVO;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogRespVO;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录日志
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@RestController
@RequestMapping("/system/loginLog")
@RequiredArgsConstructor
public class LoginLogController {

    private final LoginLogService loginLogService;

    /**
     * 分页查询登录日志
     *
     * @param reqVO 分页请求
     * @return 分页登录日志
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-登录日志", name = "分页查询登录日志")
    @SaCheckPermission("system:loginLog:query")
    public CommonResult<PageResult<LoginLogRespVO>> getLoginLogPage(@Valid LoginLogPageReqVO reqVO) {
        PageResult<LoginLogDO> page = loginLogService.getLoginLogPage(reqVO);
        return CommonResult.success(LoginLogConvert.INSTANCE.convertPage(page));
    }
}

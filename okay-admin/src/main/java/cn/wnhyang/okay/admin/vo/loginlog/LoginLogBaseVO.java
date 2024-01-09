package cn.wnhyang.okay.admin.vo.loginlog;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/8/15
 **/
@Data
public class LoginLogBaseVO {

    @NotNull(message = "日志类型不能为空")
    private Integer loginType;

    @NotBlank(message = "用户账号不能为空")
    @Size(max = 30, message = "用户账号长度不能超过30个字符")
    private String account;

    @NotNull(message = "登录结果不能为空")
    private Integer result;

    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    private String userAgent;
}

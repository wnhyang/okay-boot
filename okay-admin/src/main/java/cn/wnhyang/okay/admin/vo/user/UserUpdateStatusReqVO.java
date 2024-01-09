package cn.wnhyang.okay.admin.vo.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/8/3
 **/
@Data
public class UserUpdateStatusReqVO {

    /**
     * 用户ID
     */
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;
}

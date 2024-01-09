package cn.wnhyang.okay.admin.vo.permission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/11/16
 **/
@Data
public class UserRoleReqVO {

    @NotNull(message = "用户编号不能为空")
    private Long userId;

    private Set<Long> roleIds;
}

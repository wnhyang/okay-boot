package cn.wnhyang.okay.system.vo.user;

import cn.wnhyang.okay.system.dto.RoleSimpleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wnhyang
 * @date 2023/8/9
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRespVO extends UserBaseVO {

    private Long id;

    private List<RoleSimpleVO> roles;

    private Integer status;

    private String loginIp;

    private LocalDateTime loginDate;

    private LocalDateTime createTime;
}

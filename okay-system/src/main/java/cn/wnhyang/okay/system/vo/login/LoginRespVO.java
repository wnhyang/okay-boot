package cn.wnhyang.okay.system.vo.login;

import cn.wnhyang.okay.system.dto.RoleSimpleVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/7/24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespVO {

    private Long userId;

    private String token;

    private List<RoleSimpleVO> roles;
}

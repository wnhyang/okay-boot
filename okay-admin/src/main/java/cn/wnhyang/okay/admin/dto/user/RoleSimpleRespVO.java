package cn.wnhyang.okay.admin.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2023/11/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSimpleRespVO {

    private Long id;

    private String name;

    private String value;
}

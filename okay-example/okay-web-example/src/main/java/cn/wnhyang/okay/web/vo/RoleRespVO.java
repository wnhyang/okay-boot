package cn.wnhyang.okay.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRespVO {

    private Long id;

    private String name;

    private String value;
}

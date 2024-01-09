package cn.wnhyang.okay.admin.vo.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2023/8/8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileLoginReqVO {

    /**
     * 手机号码
     */
    private String mobile;
}

package cn.wnhyang.okay.admin.vo.rsa;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/10/11
 **/
@Data
public class RsaCreateVO {

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 备注
     */
    private String remark;
}

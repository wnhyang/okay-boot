package cn.wnhyang.okay.admin.service.impl;

import cn.hutool.crypto.asymmetric.RSA;
import cn.wnhyang.okay.admin.convert.RsaConvert;
import cn.wnhyang.okay.admin.entity.RsaPO;
import cn.wnhyang.okay.admin.mapper.RsaMapper;
import cn.wnhyang.okay.admin.service.RsaService;
import cn.wnhyang.okay.admin.vo.rsa.RsaCreateVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaPageVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaPairVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaUpdateVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 密钥表 服务实现类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
@Service
@RequiredArgsConstructor
public class RsaServiceImpl implements RsaService {

    private final RsaMapper rsaMapper;

    @Override
    public RsaPairVO generateKeyPair() {
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();
        RsaPairVO respVO = new RsaPairVO();
        respVO.setPublicKey(publicKey).setPrivateKey(privateKey);
        return respVO;
    }

    @Override
    public Long createSecretKey(RsaCreateVO reqVO) {
        RsaPO rsaDO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.insert(rsaDO);
        return rsaDO.getId();
    }

    @Override
    public void updateRsa(RsaUpdateVO reqVO) {
        RsaPO rsaDO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.updateById(rsaDO);
    }

    @Override
    public void deleteRsa(Long id) {
        rsaMapper.deleteById(id);
    }

    @Override
    public PageResult<RsaPO> getRsaPage(RsaPageVO reqVO) {
        return rsaMapper.selectPage(reqVO, null);
    }

    @Override
    public RsaPO getRsa(Long id) {
        return rsaMapper.selectById(id);
    }
}

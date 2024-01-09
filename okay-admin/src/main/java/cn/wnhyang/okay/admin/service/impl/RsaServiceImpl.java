package cn.wnhyang.okay.admin.service.impl;

import cn.hutool.crypto.asymmetric.RSA;
import cn.wnhyang.okay.admin.convert.rsa.RsaConvert;
import cn.wnhyang.okay.admin.entity.RsaDO;
import cn.wnhyang.okay.admin.mapper.RsaMapper;
import cn.wnhyang.okay.admin.service.RsaService;
import cn.wnhyang.okay.admin.vo.rsa.RsaCreateReqVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaPageReqVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaPairRespVO;
import cn.wnhyang.okay.admin.vo.rsa.RsaUpdateReqVO;
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
    public RsaPairRespVO generateKeyPair() {
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();
        RsaPairRespVO respVO = new RsaPairRespVO();
        respVO.setPublicKey(publicKey).setPrivateKey(privateKey);
        return respVO;
    }

    @Override
    public Long createSecretKey(RsaCreateReqVO reqVO) {
        RsaDO rsaDO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.insert(rsaDO);
        return rsaDO.getId();
    }

    @Override
    public void updateRsa(RsaUpdateReqVO reqVO) {
        RsaDO rsaDO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.updateById(rsaDO);
    }

    @Override
    public void deleteRsa(Long id) {
        rsaMapper.deleteById(id);
    }

    @Override
    public PageResult<RsaDO> getRsaPage(RsaPageReqVO reqVO) {
        return rsaMapper.selectPage(reqVO, null);
    }

    @Override
    public RsaDO getRsa(Long id) {
        return rsaMapper.selectById(id);
    }
}

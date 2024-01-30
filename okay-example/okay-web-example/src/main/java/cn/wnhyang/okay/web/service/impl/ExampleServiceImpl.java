package cn.wnhyang.okay.web.service.impl;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.web.convert.example.ExampleConvert;
import cn.wnhyang.okay.web.entity.ExampleDO;
import cn.wnhyang.okay.web.mapper.ExampleMapper;
import cn.wnhyang.okay.web.service.ExampleService;
import cn.wnhyang.okay.web.vo.example.ExampleCreateReqVO;
import cn.wnhyang.okay.web.vo.example.ExamplePageReqVO;
import cn.wnhyang.okay.web.vo.example.ExampleUpdateReqVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleServiceImpl implements ExampleService {

    private final ExampleMapper exampleMapper;

    @Override
    public List<ExampleDO> getExampleList() {
        return exampleMapper.selectList();
    }

    @Override
    public ExampleDO getExample(Long id) {
        return exampleMapper.selectById(id);
    }

    @Override
    public Long createExample(ExampleCreateReqVO reqVO) {
        ExampleDO example = ExampleConvert.INSTANCE.convert(reqVO);
        exampleMapper.insert(example);
        return example.getId();
    }

    @Override
    public void updateExample(ExampleUpdateReqVO reqVO) {
        exampleMapper.updateById(ExampleConvert.INSTANCE.convert(reqVO));
    }

    @Override
    public void deleteExample(Long id) {
        exampleMapper.deleteById(id);
    }

    @Override
    public PageResult<ExampleDO> getExamplePage(ExamplePageReqVO reqVO) {
        return exampleMapper.selectPage(reqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBatch(List<ExampleDO> example) {
        exampleMapper.updateBatch(example);
    }
}

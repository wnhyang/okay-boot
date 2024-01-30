package cn.wnhyang.okay.web.service.impl;

import cn.wnhyang.okay.web.entity.ExampleDO;
import cn.wnhyang.okay.web.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class ExampleServiceImplTest {
    @Resource
    private ExampleService exampleService;

    @Test
    void updateBatch() {
        List<ExampleDO> exampleList = exampleService.getExampleList();

        for (ExampleDO exampleDO : exampleList) {
            exampleDO.addField2(1);
        }
        exampleService.updateBatch(exampleList);

    }
}
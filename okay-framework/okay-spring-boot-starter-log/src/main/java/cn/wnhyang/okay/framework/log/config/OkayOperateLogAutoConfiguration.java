package cn.wnhyang.okay.framework.log.config;

import cn.wnhyang.okay.framework.log.core.aop.OperateLogAspect;
import cn.wnhyang.okay.framework.log.core.service.LogService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2023/5/31
 **/
@AutoConfiguration
public class OkayOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect(LogService logService) {
        OperateLogAspect operateLogAspect = new OperateLogAspect();
        operateLogAspect.setLogService(logService);
        return operateLogAspect;
    }

}

package cn.wnhyang.okay.admin.config;

import cn.wnhyang.okay.admin.service.impl.LoginServiceImpl;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wnhyang
 * @date 2024/1/23
 **/
@Configuration
@Slf4j
public class SaTokenConfig {

    @Bean
    public LoginService loginService() {
        log.info("[LoginService][初始化loginService配置]");
        return new LoginServiceImpl();
    }

}

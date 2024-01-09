package cn.wnhyang.okay.web.config;

import cn.wnhyang.okay.framework.web.core.service.LoginService;
import cn.wnhyang.okay.web.service.impl.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Configuration
public class SecurityConfig {

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl();
    }
}

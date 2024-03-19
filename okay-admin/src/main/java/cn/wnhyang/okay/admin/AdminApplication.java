package cn.wnhyang.okay.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wnhyang
 * @date 2024/1/7
 **/
@SpringBootApplication(scanBasePackages = {"cn.wnhyang.okay.admin", "cn.wnhyang.okay.system"})
@Slf4j
public class AdminApplication {

    public static void main(String[] args) {
        try {
            log.info("开始启动。。。");
            SpringApplication.run(AdminApplication.class, args);
        } catch (Throwable e) {
            log.error("启动失败:", e);
        }

    }
}

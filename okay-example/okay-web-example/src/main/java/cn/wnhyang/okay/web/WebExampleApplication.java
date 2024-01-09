package cn.wnhyang.okay.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@SpringBootApplication
@Slf4j
public class WebExampleApplication {

    public static void main(String[] args) {
        try {
            log.info("开始启动。。。");
            SpringApplication.run(WebExampleApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

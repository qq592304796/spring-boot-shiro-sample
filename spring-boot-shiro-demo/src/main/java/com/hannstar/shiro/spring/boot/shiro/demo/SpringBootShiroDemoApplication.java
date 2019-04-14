package com.hannstar.shiro.spring.boot.shiro.demo;

import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

/**
 * Spring Boot Shiro Demo启动类
 * @author jiangxinjun
 * @date 2019-04-02
 */
@Slf4j
//@EnableCaching
@MapperScan("com.hannstar.shiro.spring.boot.shiro.demo.mapper")
@SpringBootApplication
@Configuration
public class SpringBootShiroDemoApplication {
    
    private static RedisServer redisServer;
    
    public static void main(String[] args) throws IOException {
        log.info("spring-boot-shiro-demo is starting");
        SpringApplication app = new SpringApplication(SpringBootShiroDemoApplication.class);
        app.addListeners((ApplicationEnvironmentPreparedEvent event) -> {
            redisServer = RedisServer.builder()
                    .port(6379)
                    .setting("maxheap 128M")
                    .build();
            redisServer.start();
        });
        app.addListeners((ContextClosedEvent event) -> {
            if (redisServer != null) {
                redisServer.stop();
            }
        });
        app.run(args);
        log.info("spring-boot-shiro-demo is started");
    }
}


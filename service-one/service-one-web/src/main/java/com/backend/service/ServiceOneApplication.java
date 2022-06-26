package com.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
public class ServiceOneApplication {

    public static void main(String[] args) {
        try {
            log.info("[service-one]=====================开始启动============================");
            SpringApplication.run(ServiceOneApplication.class);
            log.info("[service-one]=====================启动完毕============================");
        } catch (Exception e) {
            log.info("[service-one]=====================启动失败============================");
            log.info("[service-one]启动error:", e);
        }
    }
}

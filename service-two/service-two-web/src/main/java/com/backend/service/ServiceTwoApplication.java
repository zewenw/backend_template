package com.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
//@EnableDubbo
//@EnableDiscoveryClient
@SpringBootApplication
public class ServiceTwoApplication {

    public static void main(String[] args) {
        try {
            log.info("[service-two]=====================开始启动============================");
            SpringApplication.run(ServiceTwoApplication.class);
            log.info("[service-two]=====================启动完毕============================");
        } catch (Exception e) {
            log.info("[service-two]=====================启动失败============================");
            log.info("[service-two]启动error:", e);
        }
    }
}

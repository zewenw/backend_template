package com.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@MapperScan(value = "com.backend.service.dao.mapper")
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
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

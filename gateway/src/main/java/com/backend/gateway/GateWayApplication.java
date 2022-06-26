package com.backend.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {

    public static void main(String[] args) {
        try {
            log.info("[gateway-server]=====================开始启动============================");
            SpringApplication.run(GateWayApplication.class);
            log.info("[gateway-server]=====================启动完毕============================");
        } catch (Exception e) {
            log.info("[gateway-server]=====================启动失败============================");
            log.info("[gateway-server]启动error:", e);
        }
    }
}

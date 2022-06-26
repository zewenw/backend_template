package com.backend.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class OauthServerApplication {

    public static void main(String[] args) {
        try {
            log.info("[oauth-server]=====================开始启动============================");
            SpringApplication.run(OauthServerApplication.class);
            log.info("[oauth-server]=====================启动完毕============================");
        } catch (Exception e) {
            log.info("[oauth-server]=====================启动失败============================");
            log.info("[oauth-server]启动error:", e);
        }
    }
}

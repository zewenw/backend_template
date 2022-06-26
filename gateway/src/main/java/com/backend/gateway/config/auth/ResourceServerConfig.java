package com.backend.gateway.config.auth;

import cn.hutool.core.convert.Convert;
import com.backend.common.constants.CodeEnum;
import com.backend.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 资源服务器配置
 */
@ConfigurationProperties(prefix = "security")
@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
@Slf4j
public class ResourceServerConfig {

    /**
     * 鉴权
     */
    @Autowired
    private ResourceServerManager resourceServerManager;
    /**
     * 验证
     */
    @Autowired
    private JwtAuthenticateManager jwtAuthenticateManager;

    /**
     * 不需要拦截校验权限的路径
     */
    @Setter
    private List<String> ignoreUrls;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        //认证过滤器，放入认证管理器tokenAuthenticationManager
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(jwtAuthenticateManager);
        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
        http
                .httpBasic().disable()
                .authorizeExchange()
                //放行白名单
                .pathMatchers(Convert.toStrArray(ignoreUrls)).permitAll()
                .anyExchange().access(resourceServerManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler()) // 处理未授权
                .authenticationEntryPoint(authenticationEntryPoint()) //处理未认证
                //跨域禁用
                .and().csrf().disable()
                //token验证
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);;

        return http.build();
    }

    /**
     * 自定义未授权响应
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.writeErrorInfo(response, CodeEnum.UNAUTHORIZED));
            return mono;
        };
    }

    /**
     * token无效或者已过期自定义响应
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.writeErrorInfo(response, CodeEnum.TOKEN_INVALID_OR_EXPIRED));
            return mono;
        };
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}

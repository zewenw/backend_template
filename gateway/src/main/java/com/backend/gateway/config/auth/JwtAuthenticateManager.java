package com.backend.gateway.config.auth;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.backend.oauth.common.model.JwtTokenConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 对令牌进行验证确保令牌真实有效
 */
@Component
@Slf4j
public class JwtAuthenticateManager implements ReactiveAuthenticationManager {

    /**
     * TODO 校验令牌是否未被篡改，过期，伪造
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        log.info("[gateway]验证令牌真实性=====================");
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof JwtAuthenticationToken)
                .cast(JwtAuthenticationToken.class)
                .map(JwtAuthenticationToken::getToken)
                .flatMap((accessToken -> {
                    //验证令牌是否过期
                    Map<String, Object> claims = accessToken.getClaims();
                    Long expirein = (Long) claims.get(JwtTokenConstant.EXP);
                    DateTime date = DateUtil.date(expirein * 1000);
                    if (DateUtil.compare(DateUtil.date(), date) >= 0) {
                        return Mono.error(new RuntimeException("token已过期！"));
                    }
                    //验证令牌是否伪造
                    //验证令牌是否篡改
                    return Mono.just(accessToken);
                })).cast(Authentication.class);
    }
}

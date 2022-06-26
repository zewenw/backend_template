package com.backend.gateway.config.auth;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.backend.oauth.common.model.JwtTokenConstant;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 判断用户是否在黑名单
 * 校验用户是否具备访问指定资源的权限
 */
@Slf4j
@Component
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * TODO 主要是对用户意向访问的资源做鉴权 判断是否具备对应权限 角色授权逻辑
     * 黑名单用户 登出逻辑
     */
    @SneakyThrows
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        log.debug("[gateway]验证用户权限是否可以访问对应资源=====================");
        //认证通过且角色匹配的用户可访问当前路径
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String token = request.getHeaders().getFirst(JwtTokenConstant.TOKEN_NAME);
        if (CharSequenceUtil.isNotBlank(token)) {
            //校验黑名单
            String payload = StrUtil.toString(JWSObject.parse(token).getPayload());
            JSONObject payLoadJsonObj = JSONUtil.parseObj(payload);
            String jti = payLoadJsonObj.getStr(JwtTokenConstant.JTI);
            boolean flag = checkBlackList(jti);
            if(flag){
                return Mono.just(new AuthorizationDecision(false));
            }
            //TODO 校验用户权限
            return Mono.just(new AuthorizationDecision(true));
        } else {
            return Mono.just(new AuthorizationDecision(false));
        }
    }

    /**
     * 黑名单用户 登出逻辑
     * @param jti jti
     * @return true:黑名单用户
     */
    private boolean checkBlackList(String jti) {
        String key = JwtTokenConstant.REDIS_KEY_PREFIX.concat(jti);
        String value = stringRedisTemplate.opsForValue().get(key);
        if (CharSequenceUtil.isNotBlank(value)) {
            return true;
        } else {
            return false;
        }
    }
}

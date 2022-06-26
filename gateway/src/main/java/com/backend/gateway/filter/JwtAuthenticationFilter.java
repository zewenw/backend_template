package com.backend.gateway.filter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.backend.oauth.common.model.JwtTokenConstant;
import com.backend.oauth.common.model.JwtUserConstant;
import com.backend.oauth.common.model.LoginUser;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 下游服务依赖oauth-common
 * 该filter仅提供将authentication中的用户信息放入request便于下游服务获取登录用户基本信息
 */
@Slf4j
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {


    /**
     * 解析JWT token并将用户信息放入请求头中便于下游服务获取用户信息
     */
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[JwtAuthenticationFilter]===========================");
        //获取请求头中的加密的用户信息
        String token = exchange.getRequest().getHeaders().getFirst(JwtTokenConstant.TOKEN_NAME);
        // 非JWT放行不做后续解析处理
        if (!(StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, JwtTokenConstant.TOKEN_PREFIX))) {
            token = StrUtil.replaceIgnoreCase(token, JwtTokenConstant.TOKEN_PREFIX, Strings.EMPTY);
            //获取用户身份信息、权限信息
            String payload = StrUtil.toString(JWSObject.parse(token).getPayload());
            JSONObject payLoadJsonObj = JSONUtil.parseObj(payload);
            String userId = payLoadJsonObj.getStr(JwtUserConstant.USER_ID);
            String username = payLoadJsonObj.getStr(JwtUserConstant.USER_NAME);
            String jti = payLoadJsonObj.getStr(JwtTokenConstant.JTI);
            Long expireIn = payLoadJsonObj.getLong(JwtTokenConstant.EXP);
            Date expireDate = DateUtil.date(expireIn * 1000);
            JSONArray tempJsonArray = payLoadJsonObj.getJSONArray(JwtTokenConstant.AUTHORITIES_NAME);
            //权限
            String[] authorities = tempJsonArray.toArray(new String[0]);
            //放入LoginVal
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(userId);
            loginUser.setUsername(username);
            loginUser.setAuthorities(authorities);
            loginUser.setJti(jti);
            loginUser.setExpireDate(expireDate);
            //放入request的attribute中
            ServerHttpRequest tokenRequest = exchange.getRequest().mutate().header(LoginUser.LOGIN_USER_ATTRIBUTE, JSONUtil.toJsonStr(loginUser)).build();
            ServerWebExchange build = exchange.mutate().request(tokenRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

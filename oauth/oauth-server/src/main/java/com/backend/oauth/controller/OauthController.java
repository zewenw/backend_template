package com.backend.oauth.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.backend.common.dto.ResultDTO;
import com.backend.oauth.common.model.JwtTokenConstant;
import com.backend.oauth.common.model.LoginUser;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * oauth 权限接口
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private KeyPair keyPair;

    @ApiOperation(value = "获取公钥")
    @GetMapping("/getPublicKey")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @ApiOperation(value = "登出")
    @GetMapping("/loginOut")
    public ResultDTO<Boolean> loginOut() {
        LoginUser loginUser = (LoginUser) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getAttribute(LoginUser.LOGIN_USER_ATTRIBUTE);
        if (loginUser != null) {
            Date expireDate = loginUser.getExpireDate();
            String jti = loginUser.getJti();
            String key = JwtTokenConstant.REDIS_KEY_PREFIX.concat(jti);
            if (expireDate == null) {
                // token 永不过期则永久加入黑名单
                stringRedisTemplate.opsForValue().set(key, null);
            } else {
                // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                long between = DateUtil.between(DateUtil.date(), expireDate, DateUnit.SECOND);
                stringRedisTemplate.opsForValue().set(key, null, between, TimeUnit.SECONDS);
            }
        }
        //找不到用户 没有登录 不存在登出
        return ResultDTO.succeed(true);
    }
}

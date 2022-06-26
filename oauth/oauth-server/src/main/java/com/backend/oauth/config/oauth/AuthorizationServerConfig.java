package com.backend.oauth.config.oauth;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.backend.oauth.common.model.JwtUserConstant;
import com.backend.oauth.model.SystemUser;
import com.backend.oauth.exception.OAuthServerWebResponseExceptionTranslator;
import com.backend.oauth.service.oauth.JwtTokenUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * Security的认证管理器，密码模式需要用到
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUserDetailService jwtTokenUserDetailService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 客户端存储策略，这里使用内存方式，后续可以存储在数据库
     */
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * 允许表单认证
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
    }

    /**
     * oauth三方客户端信息配置
     * 从oauth_client_details表中获取被允许接入的客户端详情
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService detailsService = new JdbcClientDetailsService(dataSource);
        clients.withClientDetails(detailsService);
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //访问端点配置
        endpoints
                .exceptionTranslator(new OAuthServerWebResponseExceptionTranslator())
                //密码模式所需要的authenticationManager
                .authenticationManager(authenticationManager)
                //oauth token和JWT token转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                //自定义的用户查询服务
                .userDetailsService(jwtTokenUserDetailService)
                //令牌管理服务，无论哪种模式都需要
                .tokenServices(tokenServices())
                //只允许POST提交访问令牌，uri：/oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 令牌管理服务的配置
     */
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        //JWT token自定义内容增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        //添加到增强链
        services.setTokenEnhancer(tokenEnhancerChain);
        //客户端端配置策略
        services.setClientDetailsService(clientDetailsService);
        //支持令牌的刷新
        services.setSupportRefreshToken(true);
        //令牌服务
        services.setTokenStore(new JwtTokenStore(jwtAccessTokenConverter()));

        return services;
    }

    /**
     * 使用非对称加密算法对token签名
     * 作用：JWT token和oauth token信息转换
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 从classpath下的密钥库中获取密钥对(公钥+私钥)
     * see doc/secretkey.md
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("scaffold.jks"), "123456".toCharArray());
        return factory.getKeyPair(
                "scaffold", "123456".toCharArray());
    }

    /**
     * JWT内容增强
     * 在JWT token放入一些业务自定义的内容
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = MapUtil.newHashMap();
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if(principal instanceof SystemUser){
                SystemUser user = (SystemUser) principal;
                additionalInfo.put(JwtUserConstant.USER_ID, user.getUserId());
                additionalInfo.put(JwtUserConstant.USER_NAME, user.getUsername());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            //设置jwt 过期时间为1小时
            ((DefaultOAuth2AccessToken) accessToken).setExpiration(DateUtil.offsetHour(DateUtil.date(), 12));
            return accessToken;
        };
    }
}

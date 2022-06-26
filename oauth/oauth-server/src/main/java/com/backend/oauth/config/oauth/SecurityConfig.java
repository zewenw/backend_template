package com.backend.oauth.config.oauth;

import com.backend.oauth.exception.RequestAccessDeniedHandler;
import com.backend.oauth.service.oauth.JwtTokenUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * web路径安全控制
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenUserDetailService jwtTokenUserDetailService;
    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedHandler(requestAccessDeniedHandler)
                .and()
                .authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .and()
                .authorizeRequests().antMatchers("/oauth/**").permitAll()
                // 其余所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                //关闭跨域保护
                .and()
                .csrf().disable();
    }


    /**
     * 密码加密算法
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从数据库中查询用户信息
        auth.userDetailsService(jwtTokenUserDetailService);
    }


    /**
     * AuthenticationManager对象在OAuth2认证服务中要使用，提前放入IOC容器中
     * Oauth的密码模式需要
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

package com.backend.oauth.common.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.backend.oauth.common.model.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @program: scaffold
 * @description: 获取当前操作用户信息
 * @author: wangzewen
 * @create: 2022-06-11 10:41
 **/
public class LoginUserUtils {

    public static LoginUser getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) (Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest();
        String json = request.getHeader(LoginUser.LOGIN_USER_ATTRIBUTE);
        LoginUser loginUser = JSONUtil.toBean(json, LoginUser.class);
        if(ObjectUtil.isEmpty(loginUser)){
            return null;
        }else {
            return loginUser;
        }
    }
}

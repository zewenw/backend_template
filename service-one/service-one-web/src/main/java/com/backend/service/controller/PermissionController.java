package com.backend.service.controller;

import cn.hutool.json.JSONUtil;
import com.backend.oauth.common.model.LoginUser;
import com.backend.oauth.common.utils.LoginUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

/**
 * @program: scaffold
 * @description: 用于演示gateway权限整合
 * @author: wangzewen
 * @create: 2022-06-11 10:08
 **/
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @GetMapping("/hello")
    public String hello() throws FileNotFoundException {
        log.info("[service-one]PermissionController hello");
        LoginUser currentUser = LoginUserUtils.getCurrentUser();
        return JSONUtil.toJsonStr(currentUser);
    }
}

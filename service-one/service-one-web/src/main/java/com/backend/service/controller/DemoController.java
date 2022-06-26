package com.backend.service.controller;

import com.reyun.client.OssClient;
import com.reyun.exception.OssClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequestMapping("/demo")
@Api(tags = "巨量千川对接模块")
public class DemoController {

    @Autowired
    private OssClient ossClient;

    @ApiOperation(value = "判断用户是否可以继续授权千川店铺账号")
    @GetMapping("/hello")
    public String hello() {
        log.info("[service-one]DemoController hello");
        String filePath = "C:\\bug\\target.zip";
        try {
            ossClient.upload(filePath, "/test/task.zip");
        } catch (OssClientException e) {
            e.printStackTrace();
        }
//        int i = 1/0;
//        testOss();
        return "service one-hello world！";
    }



}

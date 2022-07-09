package com.backend.service.controller;

import com.backend.common.dto.ResultDTO;
import com.backend.common.template.BizCallBack;
import com.backend.common.template.BizTemplate;
import com.backend.service.domain.consumer.DemoConsumer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "service-two demo演示模块")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoConsumer demoConsumer;

    @ApiOperation(value = "hello world")
    @GetMapping("/hello")
    public ResultDTO<String> hello(){
        log.info("[service-one]DemoController hello");
        return BizTemplate.execute(new BizCallBack<String>() {
            @Override
            public void paramCheck() {

            }

            @Override
            public String preCheck() {
                return null;
            }

            @Override
            public String execute() {
                return "[service-one]DemoController hello";
            }
        });
    }

    @ApiOperation(value = "dubboDemo")
    @GetMapping("/dubboDemo")
    public ResultDTO<String> dubboDemo(){
        log.info("[service-one]DemoController hello");
        return BizTemplate.execute(new BizCallBack<String>() {
            @Override
            public void paramCheck() {

            }

            @Override
            public String preCheck() {
                return null;
            }

            @Override
            public String execute() {
                return demoConsumer.sayName();
            }
        });
    }
}

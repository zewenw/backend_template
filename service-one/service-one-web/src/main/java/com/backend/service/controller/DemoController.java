package com.backend.service.controller;

import cn.hutool.json.JSONUtil;
import com.backend.common.dto.ResultDTO;
import com.backend.common.template.BizCallBack;
import com.backend.common.template.BizTemplate;
import com.backend.oauth.common.model.LoginUser;
import com.backend.oauth.common.utils.LoginUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "service-one demo演示模块")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ApiOperation(value = "hello world")
    @GetMapping("/hello")
    public ResultDTO<String> hello() {
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
//                int i = 1 / 0;
                return JSONUtil.toJsonStr(LoginUserUtils.getCurrentUser());
            }
        });
    }


}

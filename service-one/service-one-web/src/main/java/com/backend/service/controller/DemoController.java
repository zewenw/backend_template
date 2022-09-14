package com.backend.service.controller;

import com.backend.common.dto.ResultDTO;
import com.backend.common.template.BizCallBack;
import com.backend.common.template.BizTemplate;
import com.backend.service.gRpc.lib.GreeterGrpc;
import com.backend.service.gRpc.lib.HelloReply;
import com.backend.service.gRpc.lib.HelloRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "service-one demo演示模块")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GrpcClient(value = "grpc-server")
    private GreeterGrpc.GreeterBlockingStub stub;

    @ApiOperation(value = "hello world")
    @GetMapping("/hello")
    public ResultDTO<Object> hello() {
        log.info("[service-one]DemoController hello");
        return BizTemplate.execute(new BizCallBack<Object>() {
            @Override
            public void paramCheck() {

            }

            @Override
            public Object preCheck() {
                return null;
            }

            @Override
            public Object execute() {
//                int i = 1 / 0;
//                return JSONUtil.toJsonStr(LoginUserUtils.getCurrentUser());
                HelloRequest request = HelloRequest.newBuilder().setName("lisi").build();
                HelloReply response = stub.sayHello(request);
                return response.getMessage();
            }
        });
    }


}

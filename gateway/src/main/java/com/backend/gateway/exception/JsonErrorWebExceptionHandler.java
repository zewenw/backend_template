package com.backend.gateway.exception;

import com.backend.common.constants.CodeEnum;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常使用json包装返回
 * 和下游应用服务保持一致
 */
public class JsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    public JsonErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                        ResourceProperties resourceProperties,
                                        ErrorProperties errorProperties,
                                        ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        // 这里其实可以根据异常类型进行定制化逻辑
        Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put("data", null);
        errorAttributes.put("isSuccess", false);
        errorAttributes.put("respCode", CodeEnum.ERROR.getCode());
        errorAttributes.put("respMsg", "服务端异常，请联系客服处理！");
        return errorAttributes;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        // 这里其实可以根据errorAttributes里面的属性定制HTTP响应码
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}

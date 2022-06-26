package com.backend.oauth.exception;

import com.backend.common.constants.CodeEnum;
import com.backend.common.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * 处理oauth验证失败的异常
 */
@SuppressWarnings("all")
public class OAuthServerWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    /**
     * 业务处理方法，重写这个方法返回客户端信息
     */
    @Override
    public ResponseEntity<ResultDTO> translate(Exception e) {
        ResultDTO resultMsg = doTranslateHandler(e);
        return new ResponseEntity<>(resultMsg, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 根据异常定制返回信息
     */
    private ResultDTO doTranslateHandler(Exception e) {
        //初始值，系统错误，
        //判断异常，不支持的认证方式
        if (e instanceof UnsupportedGrantTypeException) {
            return ResultDTO.failed(CodeEnum.UNSUPPORTED_GRANT_TYPE);
            //用户名或密码异常
        } else if (e instanceof InvalidGrantException) {
            return ResultDTO.failed(CodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        return ResultDTO.failed(CodeEnum.UNAUTHORIZED);
    }
}

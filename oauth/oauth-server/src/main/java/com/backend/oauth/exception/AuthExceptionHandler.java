package com.backend.oauth.exception;

import com.backend.common.constants.CodeEnum;
import com.backend.common.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler {

    /**
     * 用户不存在
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResultDTO handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResultDTO.failed(CodeEnum.USER_NOT_EXIST);
    }

    /**
     * 用户名和密码异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidGrantException.class)
    public ResultDTO handleInvalidGrantException(InvalidGrantException e) {
        return ResultDTO.failed(CodeEnum.USERNAME_OR_PASSWORD_ERROR);
    }


    /**
     * 账户异常(禁用、锁定、过期)
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResultDTO handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return ResultDTO.failed(e.getMessage());
    }

    /**
     * token 无效或已过期
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenException.class})
    public ResultDTO handleInvalidTokenExceptionException(InvalidTokenException e) {
        return ResultDTO.failed(e.getMessage());
    }

}

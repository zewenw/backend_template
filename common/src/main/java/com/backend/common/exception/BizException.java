package com.backend.common.exception;


import com.backend.common.constants.CodeEnum;

/**
 * BizException 业务异常
 * 通过继承此异常来实现自己的业务异常
 */
public class BizException extends RuntimeException {

    private String errMsg;

    public BizException() {
    }
    
    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public BizException(CodeEnum codeEnum) {
        this.errMsg = codeEnum.getMsg();
    }


    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "ErrMsg:" + errMsg;
    }
}

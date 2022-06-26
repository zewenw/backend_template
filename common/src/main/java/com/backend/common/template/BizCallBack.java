package com.backend.common.template;


/**
 * 业务兜底异常处理
 */
public interface BizCallBack<T> {

    /**
     * 参数校验
     * 如果不通过，直接抛出BizException异常即可
     */
    void paramCheck();

    /**
     * 业务逻辑校验
     * 如果不通过，直接抛出BizException异常即可
     */
    T preCheck();

    /**
     * 具体业务逻辑执行
     */
    T execute() throws Exception;
}

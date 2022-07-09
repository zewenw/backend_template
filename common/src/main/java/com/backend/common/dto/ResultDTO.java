package com.backend.common.dto;

import com.backend.common.constants.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("全局结果返回对象")
public class ResultDTO<T> implements Serializable {

    @ApiModelProperty(value = "业务操作返回数据")
    private T data;

    @ApiModelProperty(value = "响应码")
    private String respCode;

    @ApiModelProperty(value = "详细响应信息")
    private String respMsg;

    @ApiModelProperty(value = "业务操作是否成功")
    private Boolean isSuccess;

    public static <T> ResultDTO<T> succeed2Msg(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultDTO<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultDTO<T> succeed() {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResultDTO<T> succeed(T model) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResultDTO<T> succeedWith(T datas, String code, String msg) {
        return new ResultDTO<>(datas, code, msg, true);
    }

    public static <T> ResultDTO<T> failed2Msg(String msg) {
        return failedWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> ResultDTO<T> failed(T model, String msg) {
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> ResultDTO<T> failed(T model) {
        return failedWith(model, CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
    }

    public static <T> ResultDTO<T> failed(CodeEnum codeEnum) {
        return failedWith(null, codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> ResultDTO<T> failed(String code, String msg) {
        return failedWith(null, code, msg);
    }

    public static <T> ResultDTO<T> failedWith(T datas, String code, String msg) {
        return new ResultDTO<T>(datas, code, msg, false);
    }
}

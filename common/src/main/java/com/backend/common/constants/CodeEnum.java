package com.backend.common.constants;


/**
 * 响应码枚举
 */
public enum CodeEnum {

    SUCCESS("10000", "操作成功", true),
    ERROR("20000", "业务系统异常，请联相应人员获取帮助", false),

    /**
     * oauth enum
     */
    USER_NOT_EXIST("401", "用户不存在", false),
    CLIENT_AUTHENTICATION_FAILED("401", "客户端认证失败", false),
    USERNAME_OR_PASSWORD_ERROR("401", "用户名或密码错误", false),
    UNSUPPORTED_GRANT_TYPE("401", "不支持的认证模式", false),
    NO_PERMISSION("401", "无权限访问！", false),
    UNAUTHORIZED("401", "权限验证不通过", false),
    TOKEN_INVALID_OR_EXPIRED("401", "token无效或已过期", false),
    TOKEN_ACCESS_FORBIDDEN("401", "token已被禁止访问", false),
    INVALID_TOKEN("401", "无效的token", false),
    /**
     * business enum
     */
    PARAM_ERROR("4", "参数异常", false),

    DATA_OPERATE_EXCEPTION("20001", "数据操作失败", false),
    DATA_INSERT_EXCEPTION("20002", "数据插入失败", false),
    DATA_DELETE_EXCEPTION("20003", "数据删除失败", false),
    DATA_MODIFY_EXCEPTION("20004", "数据修改失败", false),
    DATA_SELECT_EXCEPTION("20005", "数据查询失败", false),
    SYSTEM_EXECUTION_ERROR("20006", "系统执行出错", false),

    THIRD_PART_INTERFACE_INVOKE_FAIL("20100", "三方接口调用失败", false);

    private final String respCode;

    private final String respMsg;

    private final boolean isSuccess;

    CodeEnum(String respCode, String respMsg, boolean isSuccess) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.isSuccess = isSuccess;
    }

    public static CodeEnum getValue(String code) {
        for (CodeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_EXECUTION_ERROR; // 默认系统执行错误
    }

    public String getCode() {
        return respCode;
    }

    public Integer getIntCode() {
        return Integer.valueOf(respCode);
    }

    public String getMsg() {
        return respMsg;
    }
}

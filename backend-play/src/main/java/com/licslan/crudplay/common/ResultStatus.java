package com.licslan.crudplay.common;

public enum ResultStatus implements IResultStatus {
    /**
     * 状态码及对应信息
     */
    //成功状态码
    SUCCESS(0, "执行成功"),
    //参数错误：1001~1999
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    //用户错误：2001~2999
    USER_LOGIN_ERROR(2001, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2002, "账户已被禁用"),
    USER_NOT_EXIST(2003, "用户不存在");
    private int errorCode;
    private String errorMsg;
    ResultStatus(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    @Override
    public Integer errorCode() {
        return errorCode;
    }
    @Override
    public String errorMsg() {
        return errorMsg;
    }
}

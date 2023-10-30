package com.licslan.crudplay.common;

public interface IResultStatus {
    /**
     * 状态码
     * @return
     */
    Integer errorCode();
    /**
     * 异常信息
     * @return
     */
    String errorMsg();
}

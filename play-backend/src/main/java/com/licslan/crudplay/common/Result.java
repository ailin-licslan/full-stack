package com.licslan.crudplay.common;

import lombok.Data;
/**
 * 测试学习 所以加了 server-port  ip 等字段  实际开发不一定需要
 * */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private Object data;
    private Boolean succeed;
    private Long serverPort;
    private String ip;

    public Result(ResultStatus restStatus, Object data, Boolean suc, Long port, String ip) {
        this.code = restStatus.errorCode();
        this.message = restStatus.errorMsg();
        this.data = data;
        this.succeed = suc;
        this.serverPort = port;
        this.ip = ip;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static Result<Void> success(Long port, String ip) {
        return new Result<Void>(ResultStatus.SUCCESS, null, true, port, ip);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(T data, Long prot, String ip) {
        return new Result<T>(ResultStatus.SUCCESS, data, true, prot, ip);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(ResultStatus resultStatus, T data, Long port, String ip) {
        if (resultStatus == null) {
            return success(data, port, ip);
        }
        return new Result<T>(resultStatus, data, true, port, ip);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> Result<T> failure(Long port, String ip) {
        return new Result<T>(ResultStatus.PARAM_TYPE_BIND_ERROR, null, false, port, ip);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultStatus resultStatus, Long port, String ip) {
        return failure(resultStatus, null, port, ip);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultStatus resultStatus, T data, Long port, String ip) {
        if (resultStatus == null) {
            return new Result<T>(ResultStatus.PARAM_IS_INVALID, null, false, port, ip);
        }
        return new Result<T>(resultStatus, data, false, port, ip);
    }
}

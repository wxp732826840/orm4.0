package com.wxp.mybatis;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Result<T> {
    public static Integer SUCCESS = 0;
    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 信息描述
     */
    private String message;
    /**
     * 返回参数
     */
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(Integer code) {
        this.code = code;

    }

    private Result(Integer code, T data) {
        this.code = code;
        this.data = data;

    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(SUCCESS, message, data);
    }

    public static <T> Result<T> failure(Integer code, String message) {
        return failure(code, message, null);
    }

    public static <T> Result<T> failure(Integer code, String message, T data) {
        return new Result<T>(code, message, data);
    }
}
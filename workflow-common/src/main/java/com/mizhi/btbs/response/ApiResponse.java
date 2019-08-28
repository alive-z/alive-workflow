package com.mizhi.btbs.response;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
    private static final String DEFAULT_CODE = "SUCCESS";
    private static final String DEFAULT_MSG = "SUCCESS";

    private String code = DEFAULT_CODE;
    private String msg = DEFAULT_CODE;
    private T data;

    public ApiResponse() {
        super();
    }

    private ApiResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> create() {
        return new ApiResponse();
    }

    public static <T> ApiResponse<T> create(String code, String msg, Serializable data) {
        return new ApiResponse(code, msg, data);
    }

    public static <T> ApiResponse<T> create(String code, String msg) {
        return new ApiResponse(code, msg, null);
    }

    public static <T> ApiResponse<T> create(T data) {
        return new ApiResponse(DEFAULT_CODE, DEFAULT_CODE, data);
    }

    public boolean isSuccess() {
        return DEFAULT_CODE.equals(this.code);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

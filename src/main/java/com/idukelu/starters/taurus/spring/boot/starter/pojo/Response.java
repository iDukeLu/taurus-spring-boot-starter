package com.idukelu.starters.taurus.spring.boot.starter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口返回的响应，由状态码 code，信息 msg，数据 data 三部分构成，
 * code 和 msg 统称 status，由 Status 管理并制定相关状态
 *
 *  @param <T> 返回数据的类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private int code;

    private String msg;

    private T data;

    private static final Response<?> DEFAULT_SUCCESS_RESPONSE = new Response<>(Status.SUCCESS.getCode(), Status.SUCCESS.getMsg(), null);

    private static final Response<?> DEFAULT_FAILURE_RESPONSE = new Response<>(Status.FAILURE.getCode(), Status.FAILURE.getMsg(), null);

    private static final Response<?> DEFAULT_ERROR_RESPONSE = new Response<>(Status.INTERNAL_SERVER_ERROR.getCode(), Status.INTERNAL_SERVER_ERROR.getMsg(), null);

    // success
    public static Response<?> success() {
        return DEFAULT_SUCCESS_RESPONSE;
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(Status.SUCCESS.getCode(), Status.SUCCESS.getMsg(), data);
    }

    public static <T> Response<T> success(String msg, T data) {
        return new Response<T>(Status.SUCCESS.getCode(), msg, data);
    }

    public static <T> Response<T> success(int code, String msg, T data) {
        if (code < 0 || code > 1000)
            throw new RuntimeException("the success code range must be 0~1000");
        return new Response<T>(code, msg, data);
    }

    // failure
    public static Response<?> failure() {
        return DEFAULT_FAILURE_RESPONSE;
    }

    public static <T> Response<T> failure(T data) {
        return new Response<T>(Status.FAILURE.getCode(), Status.FAILURE.getMsg(), data);
    }

    public static <T> Response<T> failure(String msg, T data) {
        return new Response<T>(Status.FAILURE.getCode(), msg, data);
    }

    public static <T> Response<T> failure(int code, String msg, T data) {
        if (code < -1000 || code > 0)
            throw new RuntimeException("the success code range must be -1000~0");
        return new Response<T>(code, msg, data);
    }

    // error
    public static Response<?> error() {
        return DEFAULT_ERROR_RESPONSE;
    }

    public static <T> Response<T> error(String msg) {
        return new Response<T>(Status.INTERNAL_SERVER_ERROR.getCode(), msg, null);
    }

    public static <T> Response<T> error(int code, String msg) {
        if (code < -2000 || code > -1000)
            throw new RuntimeException("the success code range must be -1000~0");
        return new Response<T>(code, msg, null);
    }

    /**
     * 响应状态，反应业务处理的状态；成功、失败或者抛出异常，与 http 的请求状态无关
     * 成功状态码范围：1 ~ 1000
     * 失败状态码范围：-1 ~ -1000
     * 异常状态码范围：-1000 ~ -2000
     * 一般情况下，异常状态的返回只出现在服务间的调用中，不应向外暴露
     */
    public enum Status {

        SUCCESS(1, "success"),

        FAILURE(-1, "failure"),

        INTERNAL_SERVER_ERROR(-1001, "internal server error");

        private int code; // success: 1~1000、failure: -1000~-1

        private String msg;

        Status(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}

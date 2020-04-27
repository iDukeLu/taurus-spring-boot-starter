package com.idukelu.starters.taurus.spring.boot.starter.pojo.response;

import lombok.Data;
import lombok.Getter;

/**
 * 接口返回的响应，由状态码 code，信息 msg，数据 data 三部分构成，
 * code 和 msg 统称 status，由 {@link ResponseStatusEnum} 管理并制定相关状态
 * @param <T> 返回数据的类型
 * @author duke
 */
@Getter
public class Response<T> {

    public static final int MIN_SUCCESS_CODE = 1;
    public static final int MAX_SUCCESS_CODE = 999;
    public static final int MIN_FAILURE_CODE = -999;
    public static final int MAX_FAILURE_CODE = -1;
    public static final int MIN_ERROR_CODE = -1999;
    public static final int MAX_ERROR_CODE = -1000;

    private static final Response<?> DEFAULT_SUCCESS_RESPONSE =
            new Response<>(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg());

    private static final Response<?> DEFAULT_FAILURE_RESPONSE =
            new Response<>(ResponseStatusEnum.FAILURE.getCode(), ResponseStatusEnum.FAILURE.getMsg());

    private static final Response<?> DEFAULT_ERROR_RESPONSE =
            new Response<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMsg());

    private final int code;

    private final String msg;

    private T data;

    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // success

    public static Response<?> success() {
        return DEFAULT_SUCCESS_RESPONSE;
    }

    public static <T> Response<T> success(String msg) {
        return success(ResponseStatusEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Response<T> success(int code, String msg) {
        return success(code, msg, null);
    }

    public static <T> Response<T> success(T data) {
        return success(ResponseStatusEnum.SUCCESS, data);
    }

    public static <T> Response<T> success(String msg, T data) {
        return success(ResponseStatusEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Response<T> success(int code, String msg, T data) {
        assertSuccessCode(code);
        return new Response<T>(code, msg, data);
    }

    public static <T> Response<T> success(ResponseStatus responseStatus) {
        return success(responseStatus, null);
    }

    public static <T> Response<T> success(ResponseStatus responseStatus, T data) {
        assertSuccessCode(responseStatus.getCode());
        return new Response<T>(responseStatus.getCode(), responseStatus.getMsg(), data);
    }


    // failure

    public static Response<?> failure() {
        return DEFAULT_FAILURE_RESPONSE;
    }

    public static <T> Response<T> failure(String msg) {
        return failure(ResponseStatusEnum.FAILURE.getCode(), msg);
    }

    public static <T> Response<T> failure(int code, String msg) {
        return failure(code, msg, null);
    }

    public static <T> Response<T> failure(T data) {
        return failure(ResponseStatusEnum.FAILURE, data);
    }

    public static <T> Response<T> failure(String msg, T data) {
        return failure(ResponseStatusEnum.FAILURE.getCode(), msg, data);
    }

    public static <T> Response<T> failure(int code, String msg, T data) {
        assertFailureCode(code);
        return new Response<>(code, msg, data);
    }

    public static <T> Response<T> failure(ResponseStatus responseStatus) {
        return failure(responseStatus, null);
    }

    public static <T> Response<T> failure(ResponseStatus responseStatus, T data) {
        assertFailureCode(responseStatus.getCode());
        return new Response<>(responseStatus.getCode(), responseStatus.getMsg(), data);
    }


    // error

    public static Response<?> error() {
        return DEFAULT_ERROR_RESPONSE;
    }

    public static <T> Response<T> error(String msg) {
        return error(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> Response<T> error(int code, String msg) {
        assertErrorCode(code);
        return new Response<>(code, msg, null);
    }

    public static <T> Response<T> error(ResponseStatus responseStatus) {
        assertErrorCode(responseStatus.getCode());
        return new Response<>(responseStatus.getCode(), responseStatus.getMsg(), null);
    }


    /**
     * 判断成功码有些范围
     * @param code 成功码
     */
    private static void assertSuccessCode(int code) {
        if (code < MIN_SUCCESS_CODE || code > MAX_SUCCESS_CODE) {
            throw new RuntimeException("the success code range must be 1 ~ 999");
        }
    }

    /**
     * 判断失败码有些范围
     * @param code 成功码
     */
    private static void assertFailureCode(int code) {
        if (code < MIN_FAILURE_CODE || code > MAX_FAILURE_CODE) {
            throw new RuntimeException("the success code range must be -999 ~ -1");
        }
    }

    /**
     * 判断错误码有些范围
     * @param code 成功码
     */
    private static void assertErrorCode(int code) {
        if (code < MIN_ERROR_CODE || code > MAX_ERROR_CODE) {
            throw new RuntimeException("the error code range must be -1999 ~ -1000");
        }
    }
}

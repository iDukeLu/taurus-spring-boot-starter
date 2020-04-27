package com.idukelu.starters.taurus.spring.boot.starter.pojo.response;

import com.idukelu.starters.taurus.spring.boot.starter.pojo.response.ResponseStatus;

/**
 * 响应状态，反应业务处理的状态；成功、失败或者抛出异常，与 http 的请求状态无关
 * 成功状态码范围：1 ~ 1000
 * 失败状态码范围：-1 ~ -1000
 * 异常状态码范围：-1000 ~ -2000
 * 一般情况下，异常状态的返回只出现在服务间的调用中，不应向外暴露
 * @author duke
 * @date 2020.04.25
 */
public enum ResponseStatusEnum implements ResponseStatus {

    /**
     * 默认的 成功、失败、错误 响应状态
     */
    SUCCESS(1, "success"),
    FAILURE(-1, "failure"),
    INTERNAL_SERVER_ERROR(-1001, "internal server error");


    /**
     * 响应状态码：
     *  - 成功状态码范围：1 ~ 1000
     *  - 失败状态码范围：-1 ~ -1000
     *  - 异常状态码范围：-1000 ~ -2000
     */
    private final int code;

    /**
     * 响应信息
     */
    private final String msg;

    ResponseStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

package com.idukelu.starters.taurus.spring.boot.starter.pojo.response;

/**
 * TODO: 编辑说明信息
 *
 * @author duke
 * @date 2020.04.25
 */
public interface ResponseStatus {

    /**
     * 获取响应码
     * @return 响应码
     */
    int getCode();

    /**
     * 获取响应信息
     * @return 响应信息
     */
    String getMsg();
}

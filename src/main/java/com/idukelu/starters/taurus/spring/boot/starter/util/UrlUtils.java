package com.idukelu.starters.taurus.spring.boot.starter.util;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlUtils {

    private UrlUtils() {}

    /**
     * 获取一次请求响应的会话信息
     *
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @return 一次请求响应的会话信息
     */
    public static String getConversationInfo(HttpServletRequest request, HttpServletResponse response) {
        return getCompleteRequestUrl(request) + "(" + getSimpleResponseStatus(response) + ")";
    }

    /**
     * 获取完整的 URL 请求路径
     * @param request HTTP 请求
     * @return 完整的 URL 请求路径
     */
    public static String getCompleteRequestUrl(HttpServletRequest request) {
        return request.getRequestURL().append(request.getQueryString() == null ? "" : "?" + request.getQueryString()).toString().intern();
    }

    /**
     * 获取简单的响应状态，格式：状态码 原因短语
     * @param response HTTP 响应
     * @return 简单的响应状态
     */
    public static String getSimpleResponseStatus(HttpServletResponse response) {
        return HttpStatus.valueOf(response.getStatus()).toString().intern();
    }

    /**
     * 获取请求地址（protocol + ip + port）
     * @param request HTTP 请求
     * @return 请求地址
     */
    public static String getRequestAddress(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "");
    }
}

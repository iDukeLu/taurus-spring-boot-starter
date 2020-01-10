package com.idukelu.starters.taurus.spring.boot.starter.util;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
        String address = request.getRequestURL().toString().intern();
        String params = FormatRequestParams(request.getParameterMap());
        return address + params;
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
     * 格式化请求参数
     * @param requestParameters 请求参数
     * @return 格式化后的请求参数，例如：?uid=4477&status=1...
     */
    private static String FormatRequestParams(Map<String, String[]> requestParameters) {
        StringBuilder params = new StringBuilder();

        // append "?"
        if (requestParameters.size() != 0) {
            params.append("?");
        }

        // append params
        int count = 0; // the flag of Judging the end
        for (Map.Entry<String, String[]> param : requestParameters.entrySet()) {
            params.append(param.getKey()).append("=").append(param.getValue()[0]);

            // append "&"
            if (++count != requestParameters.size()) {
                params.append("&");
            }
        }
        return params.toString().intern();
    }
}

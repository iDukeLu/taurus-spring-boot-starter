package com.idukelu.starters.taurus.spring.boot.starter.handler;

import com.idukelu.starters.taurus.spring.boot.starter.pojo.Response;
import com.idukelu.starters.taurus.spring.boot.starter.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author duke
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response<String> requestMethodExceptionHandler(HttpServletRequest request, Exception e) {
        log.info("请求方式不支持：{}", e.getMessage());
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Response<?> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("全局异常捕获：{}", UrlUtils.getCompleteRequestUrl(request), e);
        return Response.error();
    }

    @ExceptionHandler(value = Error.class)
    public Response<?> defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error("全局错误捕获：{}", UrlUtils.getCompleteRequestUrl(request), e);
        return Response.error();
    }
}
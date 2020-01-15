package com.idukelu.starters.taurus.spring.boot.starter.handler;

import com.idukelu.starters.taurus.spring.boot.starter.pojo.Response;
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
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response<String> requestMethodExceptionHandler(HttpServletRequest request, Exception e) {
        String eMessage = e.getMessage();
        String message = eMessage.substring(eMessage.lastIndexOf("[") + 1, eMessage.lastIndexOf("]") - 1);
        StringBuffer requestURL = request.getRequestURL();
        log.info("全局参数校验: {}", message);
        return Response.failure(message, null);
    }

    @ExceptionHandler(value = Exception.class)
    public void defaultExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("---------------------------- 全局异常捕获 ----------------------------：", e);
    }

    @ExceptionHandler(value = Error.class)
    public void defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error("---------------------------- 全局错误捕获-----------------------------：", e);
    }
}
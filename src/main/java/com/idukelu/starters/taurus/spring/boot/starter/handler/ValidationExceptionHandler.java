package com.idukelu.starters.taurus.spring.boot.starter.handler;

import com.idukelu.starters.taurus.spring.boot.starter.pojo.Response;
import com.idukelu.starters.taurus.spring.boot.starter.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class ValidationExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<String> ValidExceptionHandler(HttpServletRequest request, Exception e) {
        String eMessage = e.getMessage();
        String message = eMessage.substring(eMessage.lastIndexOf("[") + 1, eMessage.lastIndexOf("]") - 1);
        String requestUrl = UrlUtils.getCompleteRequestUrl(request);
        log.info("参数校验失败: {} | {}", requestUrl, message);
        return Response.failure(message, null);
    }
}

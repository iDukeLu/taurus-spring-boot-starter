package com.idukelu.starters.taurus.spring.boot.starter.handler;

import com.idukelu.starters.taurus.spring.boot.starter.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<String> ValidExceptionHandler(HttpServletRequest request, Exception e) {
        String eMessage = e.getMessage();
        String message = eMessage.substring(eMessage.lastIndexOf("[") + 1, eMessage.lastIndexOf("]") - 1);
        StringBuffer requestURL = request.getRequestURL();
        log.info("全局参数校验: {}", message);
        return Response.failure(message, null);
    }
}

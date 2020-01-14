package com.idukelu.starters.taurus.spring.boot.starter.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void defaultExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("---------------------------- 全局异常捕获 ----------------------------：", e);
    }

    @ExceptionHandler(value = Error.class)
    public void defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error("---------------------------- 全局错误捕获-----------------------------：", e);
    }
}
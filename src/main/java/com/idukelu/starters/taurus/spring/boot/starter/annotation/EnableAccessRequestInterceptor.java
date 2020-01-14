package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import com.idukelu.starters.taurus.spring.boot.starter.interceptor.AccessRequestInterceptor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableRequestInterceptor(AccessRequestInterceptor.class)
public @interface EnableAccessRequestInterceptor {
}

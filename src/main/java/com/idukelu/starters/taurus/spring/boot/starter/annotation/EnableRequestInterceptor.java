package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import com.idukelu.starters.taurus.spring.boot.starter.configuration.WebConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(WebConfigurer.class)
public @interface EnableRequestInterceptor {
}

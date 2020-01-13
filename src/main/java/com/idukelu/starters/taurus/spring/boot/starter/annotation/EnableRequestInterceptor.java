package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import com.idukelu.starters.taurus.spring.boot.starter.configuration.WebConfigurer;
import com.idukelu.starters.taurus.spring.boot.starter.registrar.RequestInterceptorBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.*;

/**
 * @author duke
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({WebConfigurer.class, RequestInterceptorBeanDefinitionRegistrar.class})
public @interface EnableRequestInterceptor {
    Class<? extends HandlerInterceptor>[] value();
}

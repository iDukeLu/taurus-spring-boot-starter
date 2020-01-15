package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import org.springframework.cache.annotation.EnableCaching;

import java.lang.annotation.*;

/**
 * @author duke
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableCaching
public @interface EnableMultistageCaching {
}

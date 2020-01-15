package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import com.idukelu.starters.taurus.spring.boot.starter.configuration.CaffeineConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author duke
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableCaching
@Import(CaffeineConfiguration.class)
public @interface EnableCaffeineCaching {
}

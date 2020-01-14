package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import com.idukelu.starters.taurus.spring.boot.starter.configuration.RestTemplateConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author duke
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(RestTemplateConfiguration.class)
public @interface EnableRestTemplate {
}

package com.idukelu.starters.taurus.spring.boot.starter.annotation;

import com.idukelu.starters.taurus.spring.boot.starter.configuration.ActuatorConfiguration;
import com.idukelu.starters.taurus.spring.boot.starter.configuration.CaffeineConfiguration;
import com.idukelu.starters.taurus.spring.boot.starter.endpoint.CacheInfoContributor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author duke
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CaffeineConfiguration.class, ActuatorConfiguration.class})
public @interface EnableCaffeineCaching {
}

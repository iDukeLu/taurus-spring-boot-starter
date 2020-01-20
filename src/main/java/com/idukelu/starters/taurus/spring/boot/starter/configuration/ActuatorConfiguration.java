package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.idukelu.starters.taurus.spring.boot.starter.endpoint.CacheInfoContributor;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author duke
 */
@Configuration
public class ActuatorConfiguration {

    @Bean
    @ConditionalOnClass(InfoContributor.class)
    public CacheInfoContributor cacheInfoContributor() {
        return new CacheInfoContributor();
    }
}

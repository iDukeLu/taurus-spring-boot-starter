package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnClass(WebClient.class)
@ConditionalOnMissingBean(WebClient.class)
public class WebClientConfiguration {

    @Bean()
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}

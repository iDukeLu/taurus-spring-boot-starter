package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean()
    public RestTemplate ok3RestTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}

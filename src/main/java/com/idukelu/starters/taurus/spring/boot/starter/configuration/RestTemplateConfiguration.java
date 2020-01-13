package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnClass(RestTemplate.class)
@ConditionalOnMissingBean(RestTemplate.class)
public class RestTemplateConfiguration {

    @Bean()
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean()
    @ConditionalOnClass(OkHttp3ClientHttpRequestFactory.class)
    public RestTemplate ok3RestTemplate(RestTemplateBuilder builder) {
        return builder.requestFactory(OkHttp3ClientHttpRequestFactory.class).build();
    }

    @Bean()
    @ConditionalOnClass(HttpComponentsClientHttpRequestFactory.class)
    public RestTemplate apacheRestTemplate(RestTemplateBuilder builder) {
       return builder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
    }

//    @Bean()
//    @ConditionalOnClass(OkHttp3ClientHttpRequestFactory.class)
//    public RestTemplate ok3RestTemplate(RestTemplateBuilder builder) {
//        OkHttpClient httpClient = new OkHttpClient();
//        httpClient.connectTimeoutMillis();
//        httpClient.callTimeoutMillis();
//        httpClient.readTimeoutMillis();
//        httpClient.writeTimeoutMillis();
//
//        ConnectionPool pool = httpClient.connectionPool();
//        pool.connectionCount();
//        pool.idleConnectionCount();
//        pool.evictAll();
//
//
//        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
//        return new RestTemplate(factory);
//    }
//
//    @Bean()
//    @ConditionalOnClass(OkHttp3ClientHttpRequestFactory.class)
//    public RestTemplate apacheRestTemplate(RestTemplateBuilder builder) {
//        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
//        manager.setMaxTotal(100);
//        manager.setDefaultMaxPerRoute(10);
//
//        HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(manager).build();
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(2000);
//        factory.setReadTimeout(10000);
//        factory.setHttpClient(httpClient);
//        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//    }
}

package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnMissingBean(RestTemplate.class)
public class RestConfiguration {

    @Bean()
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean()

    @ConditionalOnClass(OkHttp3ClientHttpRequestFactory.class)
    public RestTemplate ok3RestTemplate() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.connectTimeoutMillis();
        httpClient.callTimeoutMillis();
        httpClient.readTimeoutMillis();
        httpClient.writeTimeoutMillis();

        ConnectionPool pool = httpClient.connectionPool();
        pool.connectionCount();
        pool.idleConnectionCount();
        pool.evictAll();


        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        return new RestTemplate(factory);
    }

    @Bean()
    @ConditionalOnClass(OkHttp3ClientHttpRequestFactory.class)
    public RestTemplate apacheRestTemplate() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(100);
        manager.setDefaultMaxPerRoute(10);

        HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(manager).build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(10000);
        factory.setHttpClient(httpClient);
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
}

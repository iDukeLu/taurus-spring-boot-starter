package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Configuration
public class WebConfigurer implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Map<String, HandlerInterceptor> interceptors = applicationContext.getBeansOfType(HandlerInterceptor.class);
        for (Map.Entry<String, HandlerInterceptor> entry : interceptors.entrySet()) {
            System.err.println(entry.getKey());
            registry.addInterceptor(entry.getValue());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}



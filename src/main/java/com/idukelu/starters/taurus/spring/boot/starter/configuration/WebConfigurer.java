package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.idukelu.starters.taurus.spring.boot.starter.interceptor.ConversationInfoRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    private InterceptorRegistry registry;

    private ConversationInfoRequestInterceptor conversationInfoRequestInterceptor;

    @Autowired(required = false)
    public WebConfigurer(ConversationInfoRequestInterceptor conversationInfoRequestInterceptor) {
        this.conversationInfoRequestInterceptor = conversationInfoRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        this.registry = registry;
        doAddInterceptors(conversationInfoRequestInterceptor);
    }

    private void doAddInterceptors(HandlerInterceptor interceptor) {
        if (interceptor != null) {
            registry.addInterceptor(interceptor);
        }
    }
}



package com.idukelu.starters.taurus.spring.boot.starter.registrar;

import com.idukelu.starters.taurus.spring.boot.starter.annotation.EnableRequestInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author duke
 */
public class RequestInterceptorBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(EnableRequestInterceptor.class.getName());
        Class<HandlerInterceptor>[] interceptors = (Class<HandlerInterceptor>[]) attrs.get("value");
        for (Class<HandlerInterceptor> interceptor : interceptors) {
            String className = interceptor.getName();
            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(interceptor).getBeanDefinition();
            BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, className);
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
        }
    }
}

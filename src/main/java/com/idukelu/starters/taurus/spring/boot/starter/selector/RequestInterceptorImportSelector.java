package com.idukelu.starters.taurus.spring.boot.starter.selector;

import com.idukelu.starters.taurus.spring.boot.starter.annotation.EnableRequestInterceptor;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Map;

/**
 * @author duke
 */
public class RequestInterceptorImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(EnableRequestInterceptor.class.getName());
        return (String[]) Arrays.stream((Class<HandlerInterceptor>[]) attrs.get("value")).map(Class::getName).toArray();
    }
}

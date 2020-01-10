package com.idukelu.starters.taurus.spring.boot.starter.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class LogConfigurationImportSelector implements ImportSelector {

    private static final String LOG_CHANGER_CLASS = "com.idukelu.starters.taurus.spring.boot.starter.configuration.LogChanger";

    private static final String REST_CONFIGURATION_CLASS = "com.idukelu.starters.taurus.spring.boot.starter.configuration.RestConfiguration";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{LOG_CHANGER_CLASS, REST_CONFIGURATION_CLASS};
    }
}

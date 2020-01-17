package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.idukelu.starters.taurus.spring.boot.starter.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass({CaffeineCache.class, CacheManager.class})
public class CaffeineConfiguration {

    /**
     * 基于 Caffeine 的缓存管理器
     * @return 缓存管理器
     */
    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CacheConstants.CAFFEINE);
        cacheManager.setCaffeine(caffeineCache());
        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineCache() {
        return Caffeine.newBuilder()
                .initialCapacity(CacheConstants.DEFAULT_INIT_SIZE)
                .maximumSize(CacheConstants.DEFAULT_MAX_SIZE)
                .expireAfterAccess(CacheConstants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS)
                .softValues()
                .recordStats();
    }
}


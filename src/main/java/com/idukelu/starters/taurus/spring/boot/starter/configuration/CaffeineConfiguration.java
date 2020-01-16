package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.idukelu.starters.taurus.spring.boot.starter.constant.CaffeineCaches;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnClass({CaffeineCache.class, CacheManager.class})
public class CaffeineConfiguration {

    /**
     * 基于 Caffeine 的缓存管理器
     * @return 缓存管理器
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.stream(CaffeineCaches.values())
                .map(c -> new CaffeineCache(c.name(),
                        Caffeine.newBuilder().recordStats()
                                .maximumSize(c.getMaxSize())
                                .expireAfterAccess(c.getTtl(), TimeUnit.SECONDS)
                                .build())).collect(Collectors.toList()));
        return cacheManager;
    }
}


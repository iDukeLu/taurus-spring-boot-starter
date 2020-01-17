package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.idukelu.starters.taurus.spring.boot.starter.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
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

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<Object, Object>() {
            @Override
            public Object load(@NonNull Object key) throws Exception {
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(@NonNull Object key, @NonNull Object oldValue) throws Exception {
                return oldValue;
            }
        };
    }

    private Caffeine<Object, Object> caffeineCache() {
        return Caffeine.newBuilder()
                .initialCapacity(CacheConstants.DEFAULT_INIT_SIZE)
                .maximumSize(CacheConstants.DEFAULT_MAX_SIZE)
                .expireAfterAccess(CacheConstants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS)
                .refreshAfterWrite(CacheConstants.DEFAULT_REFRESH_TIME, TimeUnit.SECONDS)
                .recordStats();
    }
}


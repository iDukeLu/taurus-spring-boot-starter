package com.idukelu.starters.taurus.spring.boot.starter.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
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


/**
 * 基于 Caffeine 的缓存配置
 * @author duke
 */
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

    /**
     * caffeine 缓存配置
     * @return caffeine 缓存
     */
    public Caffeine<Object, Object> caffeineCache() {
        return Caffeine.newBuilder()
                .initialCapacity(CacheConstants.DEFAULT_INIT_SIZE)
                .maximumSize(CacheConstants.DEFAULT_MAX_SIZE)
                .expireAfterAccess(CacheConstants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS)
                .softValues()
                .recordStats()
                .removalListener((key, value, cause) -> {
                    log.info("{} - 缓存{} | key: {}", CacheConstants.CAFFEINE, getCause(cause), key);
                    if (log.isDebugEnabled()) {
                        log.debug("{} - 缓存{} | key: {}", CacheConstants.CAFFEINE, getCause(cause), key);
                    }
                });
    }

    /**
     * 获取缓存淘汰原因
     * @param cause 淘汰原因枚举
     * @return 缓存淘汰原因
     */
    private String getCause(RemovalCause cause) {
        switch (cause.name().toUpperCase()) {
            case "COLLECTED":
                return "垃圾回收淘汰";
            case "EXPIRED":
                return "过期淘汰";
            case "EXPLICIT":
                return "手动淘汰";
            case "REPLACED":
                return "更新淘汰";
            case "SIZE":
                return "最大容量淘汰";
            default:
                return "未知策略淘汰";
        }
    }
}


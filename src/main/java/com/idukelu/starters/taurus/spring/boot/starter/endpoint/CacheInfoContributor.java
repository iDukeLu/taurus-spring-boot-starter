package com.idukelu.starters.taurus.spring.boot.starter.endpoint;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.idukelu.starters.taurus.spring.boot.starter.constant.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CacheInfoContributor implements InfoContributor {

    @Autowired
    CacheManager cacheManager;

    @Override
    public void contribute(Info.Builder builder) {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(CacheConstants.CAFFEINE);
        if (caffeineCache != null) {
            Cache<Object, Object> cache = caffeineCache.getNativeCache();
            CacheStats cacheStats = cache.stats();
            HashMap<String, Object> stats = new HashMap<>();
            stats.put("缓存请求次数", cacheStats.requestCount());
            stats.put("缓存命中次数", cacheStats.hitCount());
            stats.put("缓存命中率", cacheStats.hitRate());
            stats.put("缓存未命中次数", cacheStats.missCount());
            stats.put("缓存未命中率", cacheStats.missRate());
            stats.put("缓存加载总数", cacheStats.loadCount());
            stats.put("缓存加载成功数量", cacheStats.loadSuccessCount());
            stats.put("缓存加载失败数量", cacheStats.loadFailureCount());
            stats.put("缓存加载失败率", cacheStats.loadFailureRate());
            stats.put("缓存加载总耗时", cacheStats.totalLoadTime());
            stats.put("缓存加载平局耗时", cacheStats.averageLoadPenalty());
            stats.put("缓存驱逐次数", cacheStats.evictionCount());
            stats.put("缓存驱逐权重", cacheStats.evictionWeight());
            builder.withDetail(CacheConstants.CAFFEINE, stats);
        }
    }
}

package com.idukelu.starters.taurus.spring.boot.starter.constant;

/**
 * Caffeine 缓存定义
 */
public enum CaffeineCaches{

    TEMP(5000,10),

    DEFAULT(10000, 30);

    private int maxSize;

    private int ttl;

    CaffeineCaches(int maxSize, int ttl) {
        this.maxSize = maxSize;
        this.ttl = ttl;

    }

    public int getTtl() {
        return ttl;
    }

    public int getMaxSize() {
        return maxSize;
    }
}

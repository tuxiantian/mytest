package com.tuxt.mytest.service.impl;

import com.google.common.cache.Cache;
import com.google.common.util.concurrent.RateLimiter;
import com.tuxt.mytest.service.CacheService;
import com.tuxt.mytest.util.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {
    private Cache<String, RateLimiter> modelLimitCache = CacheUtils.createCache(4000, 60 * 60 * 24);
    private static final String KEY = "translate";

    @Override
    public void translate(String text,Long userId) throws Exception {
        String limitKey = KEY + "_" + userId;
        RateLimiter limiter;
        if (modelLimitCache.getIfPresent(limitKey) == null) {
            limiter = RateLimiter.create(1);
            modelLimitCache.put(limitKey, limiter);
        } else {
            limiter = modelLimitCache.getIfPresent(limitKey);
        }
        if (!limiter.tryAcquire()) {
            log.error("translate. text " + text+" userId"+userId);
            throw new Exception("translate engine call exceed");
        }
    }
}

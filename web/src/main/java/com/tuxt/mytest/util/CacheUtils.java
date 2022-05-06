package com.tuxt.mytest.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class CacheUtils {

    public static  <K,V> Cache<K, V> createCache(int size, long expire) {
        Cache<K, V> result =   CacheBuilder.newBuilder()
            //设置写缓存后8秒钟过期
            .expireAfterWrite(expire, TimeUnit.SECONDS)
            //设置缓存容器的初始容量为10
            .initialCapacity(1000)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(size)
            //设置要统计缓存的命中率
            .recordStats()
            //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build(
            );
        return result;
    }

}
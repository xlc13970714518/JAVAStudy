package com.xlc.demo.util;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
/**
* 本地缓存  Redis缓存 数据库
* */
public class LocalCacheTest {
    // 本地缓存
    private static Cache<String,Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES).build();
}

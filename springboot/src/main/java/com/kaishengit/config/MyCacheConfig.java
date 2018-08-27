package com.kaishengit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@Configuration
@EnableCaching
public class MyCacheConfig {

    @Autowired
    private RedisCachePropertity redisCachePropertity;

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

        //定义key的前缀
        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setExpires(redisCachePropertity.getExpires());
        return redisCacheManager;
    }


   /* @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager();
    }*/


}

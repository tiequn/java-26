package com.kaishengit.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guojiabang
 * @date 2018/8/24 0024
 */
@Configuration
public class RedissonConfig {

    @Autowired
    private RedissonConfigProperty redissonConfigProperty;

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();
        config.useSingleServer()
                .setAddress(redissonConfigProperty.getHost())
                .setTimeout(redissonConfigProperty.getTimeout());
        return Redisson.create(config);
    }

}

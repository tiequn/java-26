package com.kaishengit.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author guojiabang
 * @date 2018/8/23 0023
 */
@RestController
public class RedisController {

     Logger logger = LoggerFactory.getLogger(RedisController.class);

     @Autowired
     private RedissonClient redissonClient;

     @GetMapping("/redisson")
     public String setUserName(){
          RBucket<String> rBucket = redissonClient.getBucket("userName");
          rBucket.set("Hello");

          return "nothing";
     }





   /* private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    @GetMapping("/redis")
    public void setRedis(){
       redisTemplate.opsForValue().set("name","tom");
    }

    @GetMapping("/getredis")
    public String getRedis(){
        return redisTemplate.opsForValue().get("name");
    }*/

}

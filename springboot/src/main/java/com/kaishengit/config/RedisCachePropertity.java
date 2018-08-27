package com.kaishengit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisCachePropertity {

    private Map<String,Long> expires;

    public void setExpires(Map<String,Long> expires){
        this.expires = expires;
    }

    public Map<String,Long> getExpires(){
        return expires;
    }

}

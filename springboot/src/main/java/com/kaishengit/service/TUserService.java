package com.kaishengit.service;

import com.kaishengit.entity.TUser;
import com.kaishengit.mapper.TUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author guojiabang
 * @date 2018/8/25 0025
 */
@Service
@CacheConfig(cacheNames = "user")
public class TUserService {

    private Logger logger = LoggerFactory.getLogger(TUserService.class);

    @Autowired
    public TUserMapper tUserMapper;

    /*@Autowired
    public CacheManager cacheManager;

    @PostConstruct
    public void init(){

        Cache cache = cacheManager.getCache("user");
        TUser tUser = new TUser();
        tUser.setId(1);
        tUser.setUserName("tom");

        cache.put(1,tUser);

        logger.info("热数据加载完毕");
    }
*/

    @Cacheable
    public TUser findById(Integer id){
        return tUserMapper.selectByPrimaryKey(id);
    }

    @CacheEvict
    public void findBydel(Integer id) {

    }
}

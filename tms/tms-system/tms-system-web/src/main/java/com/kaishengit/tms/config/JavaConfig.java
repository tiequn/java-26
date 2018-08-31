package com.kaishengit.tms.config;

import com.kaishengit.tms.shiro.CustomerFilterChainDefinition;
import com.kaishengit.tms.shiro.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/31 0031
 */
@Configuration
public class JavaConfig {

    @Bean
    public Realm realm(){
        return new ShiroRealm();
    }

    public CustomerFilterChainDefinition customerFilterChainDefinition(ShiroFilterFactoryBean shiroFilterFactoryBean) throws Exception{

        CustomerFilterChainDefinition chainDefinition = new CustomerFilterChainDefinition();
        chainDefinition.setShiroFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());

        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/favicon.ico","anon");
        linkedHashMap.put("/bootstrap/**","anon");
        linkedHashMap.put("/dist/**","anon");
        linkedHashMap.put("/plugins/**","anon");
        linkedHashMap.put("/js/**","anon");
        linkedHashMap.put("/logout","logout");

        chainDefinition.setFilterChainDefinitions(linkedHashMap);
        return chainDefinition;

    }


}

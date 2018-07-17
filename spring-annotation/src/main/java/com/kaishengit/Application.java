package com.kaishengit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
@Configuration
@ComponentScan  //(basePackages = "com.kaishengit")
@EnableAspectJAutoProxy
public class Application {

}

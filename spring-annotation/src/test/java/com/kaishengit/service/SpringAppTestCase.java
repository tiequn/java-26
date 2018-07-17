package com.kaishengit.service;

import com.kaishengit.SpringTestCase;
import com.kaishengit.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
public class SpringAppTestCase extends SpringTestCase {

    @Autowired
    private UserDao userDao;

    @Test
    public void testApp(){
        userDao.save();
    }

}

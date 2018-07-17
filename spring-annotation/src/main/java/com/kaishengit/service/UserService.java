package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
@Service("userService")
public class UserService {

    private UserDao userDao;

   /* @Autowired
    public UserService(UserDao userDao){

        this.userDao = userDao;

    }*/

    public void save(){
        userDao.save();
    }



    @Autowired
   public void setUserService(UserDao userDao){
       this.userDao = userDao;
   }

}

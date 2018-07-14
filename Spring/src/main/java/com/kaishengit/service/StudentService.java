package com.kaishengit.service;

import com.kaishengit.dao.StudentDao;

/**
 * @author guojiabang
 * @date 2018/7/14 0014
 */
public class StudentService {

    private StudentDao studentDao;

    // 构造方法注入

   /* public StudentService(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    public void save(){
        studentDao.save();
    }*/


      // set 注入
    public void save(){
        studentDao.save();
    }

    public void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }
}

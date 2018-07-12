package com.kaishengit.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @author guojiabang
 * @date 2018/7/12 0012
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        Reader reader = null;
        // 读取mybatis文件
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建SqlSessionFactor
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

    }

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    public static SqlSession getSqlsession(){
        return getSqlSessionFactory().openSession();
    }

    public static SqlSession getSqlsession(Boolean aotuCommit){
        return getSqlSessionFactory().openSession(aotuCommit);
    }


}

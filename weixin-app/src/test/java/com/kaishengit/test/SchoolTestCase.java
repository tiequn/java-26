package com.kaishengit.test;

import com.kaishengit.entity.School;
import com.kaishengit.mapper.SchoolMapper;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guojiabang
 * @date 2018/7/13 0013
 */
public class SchoolTestCase {

    Logger logger = LoggerFactory.getLogger(SchoolTestCase.class);

    private SqlSession sqlSession;
    private SchoolMapper schoolMapper;

    @Before
    public void before(){

        sqlSession = MybatisUtils.getSqlSession();
        // 动态代理：Sqlsession对象根据接口class动态创建接口实现类
        schoolMapper = sqlSession.getMapper(SchoolMapper.class); // 获得接口的实现类对象
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testById(){
        School school = schoolMapper.findBySchoolId(1);
        logger.debug("school:{}",school);
    }

}

package com.kaishengit.test;

import com.kaishengit.entity.School;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.Tag;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/11 0011
 */
public class StiudentTestCase {

    Logger logger = LoggerFactory.getLogger(StiudentTestCase.class);

    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void before() {
        sqlSession = MybatisUtils.getSqlSession();
        // 动态代理：sqlSession对象根据接口的class动态创建接口的实现类
        studentMapper = sqlSession.getMapper(StudentMapper.class);// 获得接口的实现类对象
    }

    @After
    public void after() {
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testAddBatch(){
        // 批量插入
        Tag tag = new Tag();
        tag.setTagName("幽默");

        Tag tag1 = new Tag();
        tag1.setTagName("搞笑");

        List<Tag> tagList = Arrays.asList(tag,tag1);
        int count = studentMapper.addBatch(tagList);
        sqlSession.commit();
        logger.debug("size:{}",count);

    }

    @Test
    public void testFindWithTagById(){
        // 一对多查询
        Student student = studentMapper.findWithTagById(1);
        logger.debug("student:{}",student);
    }

    @Test
    public  void testFindWithSchoolById(){
        // 一对一查询
        Student student = studentMapper.findWithSchoolById(2);
        logger.debug("student:{}",student);
    }

    @Test
    public void testfingStudentById(){
       // 多对一查询
        School school = studentMapper.findStudentById(1);
        logger.debug("student:{}",school);
    }



}

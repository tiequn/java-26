package com.kaishengit.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/12 0012
 */
public class ExampleTestCase {

    Logger logger = LoggerFactory.getLogger(ExampleTestCase.class);

    private SqlSession sqlSession;
    private StudentMapper studentMapper;
    @Before
    public void before(){
        // 加载前运行的
        sqlSession = MybatisUtils.getSqlsession();
        // 动态代理：Sqlsession对象根据接口的Class动态创建接口的实现类
       studentMapper = sqlSession.getMapper(StudentMapper.class); // 获得实现类接口的对象
    }

    @After
    public void after(){
        // 运行后加载
        sqlSession.close();
    }

    @Test
    public void testInsertById(){
        Student student = new Student();
        student.setUserName("范霞");
        //student.setAddress("广东");
        student.setSchoolId(5);

        int count = studentMapper.insert(student);
        logger.debug("受影响的行数:{}",count);

        // 自动增长的主键
        int id = student.getId();
        logger.debug("自动增长的主键值：{}",id);

        // 提交
        sqlSession.commit();
    }

    @Test
    public void testSelectiveByInsert(){
        Student student = new Student();
        student.setUserName("候光");
        student.setSchoolId(4);

        int count = studentMapper.insertSelective(student);
        logger.debug("受影响的行数:{}",count);

        // 自动增长的主键
        int id = student.getId();
        logger.debug("自动增长的主键值：{}",id);

        // 提交
        sqlSession.commit();

    }

    @Test
    public void tesDelById(){
        studentMapper.deleteByPrimaryKey(14);
        sqlSession.commit();
    }

    @Test
    public void tesDelByExample(){
        // 根据对象删除
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSchoolIdEqualTo(6);

        studentMapper.deleteByExample(studentExample);
        sqlSession.commit();

    }

    @Test
    public void testUpdateById(){
        Student student = studentMapper.selectByPrimaryKey(12);
        student.setAddress("濮阳");
        student.setUserName("范晓霞");
        student.setSchoolId(5);

        studentMapper.updateByPrimaryKey(student);

        sqlSession.commit();

    }

    @Test
    public void testUpdatePrimaryKeySelectiveById(){
        Student student = studentMapper.selectByPrimaryKey(13);
        //student.setUserName("周帅");
        student.setAddress("新乡");
        studentMapper.updateByPrimaryKeySelective(student);

        sqlSession.commit();
    }

    @Test
    public void testSelectById(){
        Student student = studentMapper.selectByPrimaryKey(1);
        logger.debug("student:{}",student);

    }

    @Test
    public void testSelectAll(){
        StudentExample studentExample = new StudentExample();

        /*
        // 或者筛选
        studentExample.or().andSchoolIdEqualTo(3);
        studentExample.or().andSchoolIdEqualTo(2);
        */
        studentExample.setOrderByClause("id desc"); // 排序 降序
        studentExample.setDistinct(true); // 去重

        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for(Student student : studentList){
            System.out.println(student);
        }

    }

    @Test
    public void testListBySelect(){
        StudentExample studentExample = new StudentExample();
        /*StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andSchoolIdEqualTo(1);
        criteria.andUserNameLike("%帅%");*/

        studentExample.createCriteria().
                andSchoolIdEqualTo(1)
                .andUserNameLike("%帅%");

        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for(Student student : studentList){
            System.out.println(student);
        }

    }

    @Test
    public void testPage(){

        // PageHelper.startPage(0,3);
        PageHelper.offsetPage(3,3);
        StudentExample studentExample = new StudentExample();

        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for (Student student : studentList){
            System.out.println(student);
        }

        PageInfo<Student> studentPageInfo = new PageInfo<>(studentList);
        System.out.println(studentPageInfo.getPageNum());
        System.out.println(studentPageInfo.getPageSize());

    }


    

}

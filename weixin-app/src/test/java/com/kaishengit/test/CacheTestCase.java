package com.kaishengit.test;

import com.kaishengit.entity.Product;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.MessageUtils;


/**
 * @author guojiabang
 * @date 2018/7/12 0012
 */
public class CacheTestCase {

    Logger logger = LoggerFactory.getLogger(CacheTestCase.class);

    @Test
    public void testCache(){

        // 创建SqlSessionFactory
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 动态代理获得productMapper接口实现类的对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        Product product = productMapper.findById(4);
        logger.debug("product:{}",product);
        // 关闭资源
        sqlSession.close();


        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        ProductMapper productMapper1 = sqlSession1.getMapper(ProductMapper.class);

        Product product1 = productMapper1.findById(4);

        logger.debug("product1:{}",product1);
        sqlSession1.close();



    }


}

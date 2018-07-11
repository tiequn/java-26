package com.kaishengit.test;

import com.kaishengit.entity.Product;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/9 0009
 */
public class ProductTestCase {

    Logger logger = LoggerFactory.getLogger(ProductTestCase.class);

    @Test
    public void testSave() throws IOException {

        // 1.读取mybatis主配置文件
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        // InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        // 2.创建SqlSessionFactory（factory工厂）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 3.创建sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // 自动提交 默认值false

        // 4.操作数据库
        Product product = new Product();
        product.setProductName("iphone 8");
        product.setProductInventory("200");

        int res = sqlSession.insert("com.kaishengit.mapper.ProductMapper.save",product);

        // 4.1自动提交事物
        // sqlSession.commit();

        // 5.释放资源
        sqlSession.close();

    }
    @Test
    public void testFindAll(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Product> productList = sqlSession.selectList("com.kaishengit.mapper.ProductMapper.findAll");

        for (Product product : productList){
            logger.debug("product: {}", product.toString());
            //System.out.println(product);
        }
        sqlSession.close();
    }
    @Test
    public void testFindOne(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Product product = sqlSession.selectOne("com.kaishengit.mapper.ProductMapper.findAll",1);

        //System.out.println(product);
        logger.debug("product: {}",product.toString());
        sqlSession.close();
    }
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession(true);
        sqlSession.delete("com.kaishengit.mapper.ProductMapper.findDelById",1);

        sqlSession.close();
    }
    @Test
    public void testUpdate() {
        SqlSession sqlSession = MybatisUtils.getSqlSession(true);

        Product product = sqlSession.selectOne("com.kaishengit.mapper.ProductMapper.findById",4);

        product.setProductName("三星");
        product.setProductInventory("300");

        sqlSession.update("com.kaishengit.mapper.ProductMapper.update", product);
        sqlSession.close();
    }

}

package com.kaishengit.test;

import com.kaishengit.entity.Product;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/10 0010
 */
public class ProductMapperTestCase {

    Logger logger = LoggerFactory.getLogger(ProductMapperTestCase.class);

    private SqlSession sqlSession;
    private ProductMapper productMapper;

    @Before
    public void before() {
        // 运行前加载
        sqlSession = MybatisUtils.getSqlSession();
        // 动态代理：sqlSession对象根据接口的class动态创建接口的实现类
        productMapper = sqlSession.getMapper(ProductMapper.class);  // 获得接口的实现类对象
    }

    @After
    public void after(){
        // 运行后加载
        sqlSession.close();
    }

    @Test
    public void testSave(){
        Product product = new Product();
        product.setProductName("vivo x20");
        product.setProductInventory("200");
        int count = productMapper.save(product);
        logger.debug("受影响行数：{}",count);

        // 自动增长的主键值
        int id = product.getId();
        logger.debug("自动增长的主键：{}", id);
        // 提交
        sqlSession.commit();

    }

    @Test
    public void testFindAll(){
        List<Product> productList = productMapper.findAll();
        for(Product product : productList){
            logger.debug("product: {}", product);
        }
    }

    @Test
    public void testFindById() {
        Product product = productMapper.findById(9);
        logger.debug("product：{}" , product.toString());
    }

    @Test
    public void testFindByPage(){
        List<Product> productList = productMapper.findByPage(0,3);
        for(Product product : productList) {
            logger.debug("product: {}",product.toString());
        }
    }

    @Test
    public void testFindPageByMap(){
        Map<String,Integer> maps = new HashMap<>();
        maps.put("start", 0);
        maps.put("pageSize", 3);

        List<Product> productList = productMapper.findPageByMap(maps);
        for(Product product : productList){
            logger.debug("product:{}", product.toString());
        }

    }

    @Test
    public void testUpdate(){
        Product product = productMapper.findById(5);
        product.setProductInventory("150");

        productMapper.update(product);
        sqlSession.commit();
    }

    @Test
    public void testDeleteById(){
        productMapper.findDelById(5);
        sqlSession.commit();
    }
}

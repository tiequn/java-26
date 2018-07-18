package com.kaishengit.dao;

import com.kaishengit.SpringTestCase;
import com.kaishengit.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
public class ProductDaoTestCase extends SpringTestCase {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testSave(){
        Product product = new Product();
        product.setProductName("华为 麦芒6");
        product.setProductInventory(100);

        productDao.save(product);
    }

    @Test
    public void findById() {
        Product product = productDao.findById(11);
        System.out.println(product);
    }

    @Test
    public void findAll() {
        List<Product> productList = productDao.findAll();
        for (Product product : productList){
            System.out.println(product);
        }

    }

    @Test
    public void findMapByList() {
        List<Map<String, Object>> mapList = productDao.findMapByList();
        for (Map<String, Object> maps : mapList){
            System.out.println(maps);
        }

    }

    @Test
    public void count() {
        int count = productDao.count();
        System.out.println(count);
        Assert.assertEquals(7,count);
    }

    @Test
    public void update() {
        Product product = productDao.findById(9);
        product.setProductName("oppo");
        product.setProductInventory(10);

        productDao.update(product);
    }

    @Test
    public void findDelById() {
        productDao.findDelById(10);

    }
}

package com.kaishengit.service;

import com.kaishengit.SpringTestCase;
import com.kaishengit.entity.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
public class ProductTestCase extends SpringTestCase {

    @Autowired
    private ProductService productService;

    @Test
    public void batchSave()throws Exception{

        Product product = new Product();
        product.setProductName("黑马");
        product.setProductInventory(88);

        Product product1 = new Product();
        product1.setProductName(null);
        product1.setProductInventory(66);

        List<Product> productList = Arrays.asList(product,product1);
        productService.batchSave(productList);


    }

}

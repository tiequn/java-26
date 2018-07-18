package com.kaishengit.service;

import com.kaishengit.dao.ProductDao;
import com.kaishengit.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional(rollbackFor = Exception.class) // 事物回滚
    public void batchSave(List<Product> productList){
        for (Product product : productList){
            productDao.save(product);
        }

    }

}

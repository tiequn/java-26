package com.kaishengit.mapper;

import com.kaishengit.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/10 0010
 */
public interface ProductMapper {

    /**
     * 新增产品
     * @param product
     * @return
     */
    int save (Product product);
    List<Product> findAll();
    List<Product> findByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    List<Product> findPageByMap(Map<String, Integer> map);
    Product findById(Integer id);
    void findDelById(Integer id);
    void update(Product product);



}

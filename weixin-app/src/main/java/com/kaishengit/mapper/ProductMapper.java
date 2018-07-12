package com.kaishengit.mapper;

import com.kaishengit.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/10 
 */
public interface ProductMapper {

    /**
     * 新增产品
     * @param product
     * @return int
     */
    int save (Product product);

    /**
     * 查询所有
     * @return List<Product>
     */
    List<Product> findAll();

    /**
     *分页
     * @param start
     * @param pageSize
     * @return  List<Product>
     */
    List<Product> findByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    /**
     *使用Map集合
     * @param map
     * @return List<Product>
     */
    List<Product> findPageByMap(Map<String, Integer> map);

    /**
     *根据id查询
     * @param id
     * @return Integer
     */
    Product findById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void findDelById(Integer id);

    /**
     * 更新
     * @param product
     */
    void update(Product product);



}

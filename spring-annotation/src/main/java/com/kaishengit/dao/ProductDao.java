package com.kaishengit.dao;

import com.kaishengit.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/17 0017
 */
@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Product product){
        String sql = "insert into product(product_name, product_inventory) values(?,?)";
        jdbcTemplate.update(sql, product.getProductName(),product.getProductInventory() );
    }

   public Product findById(Integer id){
        String sql = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class),id);
   }

   public List<Product> findAll(){
        String sql = "select * from product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
   }

   public List<Map<String, Object>> findMapByList(){
        String sql = "select * from product";
        return jdbcTemplate.query(sql,new ColumnMapRowMapper());
   }

   public int count(){
        String sql = "select count(*) from product";
        return jdbcTemplate.queryForObject(sql ,new SingleColumnRowMapper<Long>()).intValue();
   }

   public void update(Product product){
        String sql = "update product set product_name = ?, product_inventory = ? where id = ?";
        jdbcTemplate.update(sql, product.getProductName(), product.getProductInventory(),product.getId());
   }

   public void findDelById(Integer id){
        String sql = "delete from product where id = ?";
        jdbcTemplate.update(sql,id);
   }




}

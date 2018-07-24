package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Parts;
import com.kaishengit.mapper.PartsMapper;
import com.kaishengit.service.PartsService;
import com.kaishengit.uril.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/23 0023
 */
@Service
public class PartsServiceImpl implements PartsService {

    @Autowired
   private PartsMapper partsMapper;

    /**
     * 根据id查询对应的配件对象
     *
     * @param id
     * @return
     */
    @Override
    public Parts findById(Integer id) {
        return partsMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据页码获得对应的数据集合
     * @param pageNo 页码 1 2 3
     * @return pageInfo 对象
     */
    @Override
    public PageInfo<Parts> findPage(Integer pageNo) {

        // 分页
        PageHelper.startPage(pageNo,Constant.DEFAULT_PAGE_SIZE);

        List<Parts> partsList = partsMapper.findPageWithType();
        // 封装分页对象
        PageInfo<Parts> pageInfo =  new PageInfo<>(partsList);

        return pageInfo;
    }
}

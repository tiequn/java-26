package com.kaisheng.it.service;

import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.ServiceType;
import com.kaisheng.it.entity.Type;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
public interface OrderService {

    /**
     * 查询所有类型
     * @return
     */
    List<ServiceType> findAllSericeType();

    /**
     * 获得所有的配件类型
     * @return
     */
    List<Type> findAllPartsType();
}

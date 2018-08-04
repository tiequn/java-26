package com.kaisheng.it.service.impl;

import com.kaisheng.it.entity.*;
import com.kaisheng.it.mapper.ServiceTypeMapper;
import com.kaisheng.it.mapper.TypeMapper;
import com.kaisheng.it.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有类型
     * @return
     */
    @Override
    public List<ServiceType> findAllSericeType() {
        ServiceTypeExample serviceTypeExample = new ServiceTypeExample();
        return serviceTypeMapper.selectByExample(serviceTypeExample);

    }

    /**
     * 获得所有的配件类型
     * @return
     */
    @Override
    public List<Type> findAllPartsType() {

        TypeExample typeExample = new TypeExample();
        return typeMapper.selectByExample(typeExample);

    }


}

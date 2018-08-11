package com.kaishengit.service.impl;

import com.kaishengit.service.ProviderService;

/**
 * @author guojiabang
 * @date 2018/8/11 0011
 */
public class ProviderServiceImpl implements ProviderService {

    public String findBId(Integer id) {
        if(id.equals(1001)){
            return "jack,Hello";
        } else {
            return "tom";
        }
    }

}

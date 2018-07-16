package com.kaishengit.proxy.cglib;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
public class ProxySale extends Sale {

    @Override
    public void salePc() {
        System.out.println("售前服务");
        super.salePc();
        System.out.println("售后服务");
    }
}

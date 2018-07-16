package com.kaishengit.proxy.jdk;

/**
 * @author guojiabang
 * @date 2018/7/16 0016
 */
public class Lenovo implements Sale {


    @Override
    public void salePc() {

        System.out.println("联想厂商销售电脑一台");

    }

    @Override
    public int salePrice(){

        System.out.println("售价100元");
        return 100;
    }
}

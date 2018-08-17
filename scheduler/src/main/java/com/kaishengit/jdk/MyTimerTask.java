package com.kaishengit.jdk;

import java.util.TimerTask;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {

        System.out.println("begin-------------------->");
        System.out.println("jdk timer is running..... ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("enb.......................");
    }

}

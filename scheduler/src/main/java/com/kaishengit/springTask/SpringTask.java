package com.kaishengit.springTask;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
public class SpringTask implements Runnable {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " spring task begin...");
        System.out.println("this is spring task running...");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end-------------------");

    }

}

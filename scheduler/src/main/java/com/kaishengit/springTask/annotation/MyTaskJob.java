package com.kaishengit.springTask.annotation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
@Component
public class MyTaskJob {

    // @Scheduled(fixedDelay = 1000)
    public void fixedJob(){
        System.out.println("this is fixed job: " + System.currentTimeMillis());
    }

    // @Scheduled(fixedDelay = 2000)
    public void delayJob(){
        System.out.println("delayJob: " + System.currentTimeMillis());
    }

    // @Scheduled(fixedDelay = 2000,initialDelay = 5000)
    public void initDelayJob() {
        System.out.println("initDelayJob: " + System.currentTimeMillis());
    }

    //@Scheduled(cron = "0/5 * * * * *")
    public void cronJob() {
        System.out.println("cronJob : " + System.currentTimeMillis());
    }


    /*
    * 异步
    */
    @Scheduled(fixedDelay = 1000)
    @Async
    public void asyenhJOb(){
        System.out.println("begin------------->");

        try {
            System.out.println("this is asyen job  ");
            Thread.sleep(1000);
            if(1 == 1){
                throw new RuntimeException("出异常了.....");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end>>>>>>>>>>>>>>");
    }



}

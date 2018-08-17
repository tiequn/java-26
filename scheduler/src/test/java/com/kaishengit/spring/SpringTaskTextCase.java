package com.kaishengit.spring;

import com.kaishengit.springTask.SpringTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task.xml")
public class SpringTaskTextCase {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Test
    public void textRun() throws IOException {

        // 立即执行
        // taskScheduler.execute(new SpringTask());

        // 在指定时间执行任务
        // taskScheduler.schedule(new SpringTask(), new Date(System.currentTimeMillis() + 3000));

        // 延迟循环执行任务
        // taskScheduler.scheduleAtFixedRate(new SpringTask(),1000);

        // 从指定时间开始延迟循环执行任务
        //taskScheduler.scheduleWithFixedDelay(new SpringTask(),new Date(),1000);

        // 使用trigger cron表达式
        taskScheduler.schedule(new SpringTask(), new CronTrigger("0/5 * * * * ?"));

        System.in.read();
    }


}

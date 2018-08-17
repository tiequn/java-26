package com.kaishengit.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.Timer;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
public class MyQuartzJobTextCase {

    @Test
    public  void textSimpleTrigger() throws SchedulerException, IOException {

        // 创建jobDetail指定执行的任务
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).build();

        // 定义trigger何时触发任务
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
        // 设置时间间隔  单位秒
        ssb.withIntervalInSeconds(5);
        // 永不间断
        ssb.repeatForever();

        Trigger simpleTrigger = TriggerBuilder.newTrigger().withSchedule(ssb).build();

        // 通过schedule调度任务
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();

        System.in.read();

    }


    @Test
    public void textCronTrigger() throws SchedulerException, IOException {

        // 创建jobDetail指定执行的任务
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).build();

        // 根据cron表达式来确定任务触发的时间点  秒、分、时、日、月、周几、年（可省略）
        ScheduleBuilder ssb = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");

        Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(ssb).build();

        // 通过schedule调度任务
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();

        System.in.read();

    }



}

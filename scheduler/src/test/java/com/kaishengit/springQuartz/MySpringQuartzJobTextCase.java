package com.kaishengit.springQuartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/8/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-quartz2.xml")
public class MySpringQuartzJobTextCase {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private int taskId = 1001;

    @Test
    public void testRun() throws IOException {
        System.out.println("容器启动成功...");
        System.in.read();
    }

    @Test
    public void addTaskText(){

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("message","come for dinner ? ");

            JobDetail jobDetail = JobBuilder.newJob(SendWeixinJob.class)
                    .withIdentity("task:" + taskId,"weixin")
                    .setJobData(jobDataMap)
                    .build();

            String cronExpression = "0 5 11 17 8 ? *";
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void delJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.deleteJob(new JobKey("task:" + taskId, "weixin"));
    }

}

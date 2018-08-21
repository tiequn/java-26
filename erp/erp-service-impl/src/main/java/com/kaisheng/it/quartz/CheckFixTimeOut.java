package com.kaisheng.it.quartz;

import com.kaisheng.it.service.FixOrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

/**
 * @author guojiabang
 * @date 2018/8/19 0019
 */
public class CheckFixTimeOut implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String jobName = jobExecutionContext.getJobDetail().getKey().getName();

        // 获取spring容器
        try {
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            FixOrderService fixOrderService = applicationContext.getBean(FixOrderService.class);

            fixOrderService.addFixOrderTimeout(jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


}

package com.kaishengit.springQuartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * @author guojiabang
 * @date 2018/8/17 0017
 */
public class SendWeixinJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap = (JobDataMap) jobExecutionContext.getMergedJobDataMap();
        System.out.println("spring quartz running ..." + jobDataMap.get("message"));

    }
}

package com.kaisheng.it.quartz;

import com.kaisheng.it.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

/**
 * @author guojiabang
 * @date 2018/8/17 0017
 */
public class CountDaily implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取spring容器
        try {
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            OrderService orderService = applicationContext.getBean(OrderService.class);
            orderService.countDailyOrder();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}

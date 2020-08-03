package com.scheduling.demo.task;

import com.scheduling.demo.constant.CronSet;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @类 名 : MyJobTask
 * @功能描述 :
 * @作 者 : inunda
 * @创建时间: 2020-08-03 16:24
 */
@Component
@EnableScheduling
public class MyJobTask implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //支持多任务
        Runnable myTask = new Runnable() {
            @Override
            public void run() {
                //任务逻辑
                System.out.println("北冥有鱼，其名为菜虚鲲。菜虚鲲之大，一锅装不下......");
            }
        };

        //触发器
        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //注意:springboot整合的quartz定时器只支持6位时域，七位时域无法正常使用
                String cron = CronSet.CRON;
                CronTrigger trigger = new CronTrigger(cron);
                return trigger.nextExecutionTime(triggerContext);
            }
        };
        scheduledTaskRegistrar.addTriggerTask(myTask, trigger);
    }
}

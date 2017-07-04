package com.mark.test.framework.utils;

import com.mark.test.framework.utils.task.PrintHelloTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Mark on 2017/7/3.
 */
public class SchedulerManager implements Job{
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "MARK-GROUP";
    private static String TRIGGER_GROUP_NAME = "MARK-TRIGGER";
    Scheduler scheduler;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(SchedulerManager.class);


    /**
     * 指定job实现类创建jobdetail对象
     * @param clz
     * @return
     */
    private JobDetail getJobDetail(Class<? extends Job> clz){
        return newJob(clz).withIdentity(clz.getSimpleName()).build();
    }

    /**
     * 获取触发器
     * @param cronExp
     * @return
     */
    private Trigger getCronTrigger(String cronExp){
        return newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule(cronExp).withMisfireHandlingInstructionFireAndProceed()).build();
    }

    private Trigger getIntervalTrigger(){
        return newTrigger().withSchedule(
                DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withIntervalInSeconds(5).withRepeatCount(1000)).build();
    }

    /**
     * 添加调度任务
     * @param clz
     * @param cronExp
     */
    public void addScheJob(Class<? extends Job> clz,String cronExp){
        try {

            scheduler = gSchedulerFactory.getScheduler();
            scheduler.scheduleJob(this.getJobDetail(clz), this.getCronTrigger(cronExp));
            scheduler.start();
            if (scheduler.isStarted()){
                logger.info("Job start up : {}",scheduler.getSchedulerName());
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void addScheJob(Class<? extends Job> clz){
        try {

            scheduler = gSchedulerFactory.getScheduler();
            scheduler.scheduleJob(this.getJobDetail(clz), this.getIntervalTrigger());
            scheduler.start();
            if (scheduler.isStarted()){
                logger.info("Job start up : {}",scheduler.getSchedulerName());
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("测试定时任务");
    }

    public static void main(String[] args) {
        SchedulerManager schedulerManager = new SchedulerManager();
        schedulerManager.addScheJob(SchedulerManager.class);
        schedulerManager.addScheJob(PrintHelloTask.class,"* * * * * ?");

    }
}

package com.mark.test.framework.core.utils;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Mark on 2017/7/3.
 */
public class SchedulerManager {
    private static SchedulerFactory gSchedulerFactory;
    private static Scheduler scheduler;
    private static String JOB_GROUP_NAME = "MARK-GROUP";
    private static String TRIGGER_GROUP_NAME = "MARK-TRIGGER";
    private static final String PACKAGENAME = "com.mark.test.framework.core.task.";
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SchedulerManager.class);

    static {
        try {
            gSchedulerFactory = new StdSchedulerFactory();
            scheduler = gSchedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            logger.error("[{}] - 实例化Schedule对象失败....",SchedulerManager.class.getSimpleName());
            e.printStackTrace();
        }
    }

    /**
     * 根据文件名获取对应任务的类对象
     * @param jobname
     * @return
     * @throws ClassNotFoundException
     */
    private Class getTaskClass(String jobname) throws ClassNotFoundException {
        assert jobname!=null;
        Class claz = Class.forName(PACKAGENAME + jobname);
        logger.info("Task claz instance is {}",claz);
        return claz;
    }

    /**
     * 指定job实现类创建jobdetail对象
     * @param jobname   任务名称
     * @return
     */
    private JobDetail getJobDetail(String jobname){
        Class clz = null;
        try {
            clz = this.getTaskClass(jobname);
            assert clz != null;
            JobDetail newjobDetail = newJob(clz).withIdentity(clz.getSimpleName()).requestRecovery().build();
            if (!"".equals(newjobDetail)){
                return newjobDetail;
            }else {
                throw new RuntimeException("Create jobDetails failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取触发器
     * @param cronExp   cron表达式
     * @return
     */
    private Trigger getCronTrigger(String cronExp){
        return newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule(cronExp).withMisfireHandlingInstructionFireAndProceed()).build();
    }

    /**
     * 创建触发器
     * @param interval      时间间隔
     * @param intervalUnit  单位
     * @return
     */
    private Trigger getIntervalTrigger(int interval, DateBuilder.IntervalUnit intervalUnit){
        return newTrigger().withSchedule(
                DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onEveryDay().withInterval(interval,intervalUnit)).build();
    }

    /**
     * 添加调度任务
     * @param taskName       job实现类
     * @param cronExp   定时任务表达式
     */
    public void addScheJob(String taskName,String cronExp){
        try {
            scheduler.scheduleJob(this.getJobDetail(taskName), this.getCronTrigger(cronExp));
            scheduler.start();
            if (scheduler.isStarted()){
                logger.info("Schedule Job : {} start up",scheduler.getSchedulerName());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增定时任务job
     * @param taskname  定时任务实现类
     * @param interval  时间间隔
     * @param unitType  时间间隔单位
     */
    public void addScheJob(String taskname,int interval,DateBuilder.IntervalUnit unitType){
        try {
            scheduler.scheduleJob(this.getJobDetail(taskname), this.getIntervalTrigger(interval,unitType));
            scheduler.start();
            if (scheduler.isStarted()){
                logger.info("Schedule Job : {} start up",scheduler.getSchedulerName());
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即启动一次
     * @param taskName
     */
    public void runNowOnce(String taskName){
        assert taskName != null;
        try {
            scheduler = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = this.getJobDetail(taskName);
            assert jobDetail != null;
            logger.info("Job key : {}",jobDetail.getKey().toString());
            scheduler.scheduleJob(jobDetail,TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow()).build());
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SchedulerManager schedulerManager = new SchedulerManager();
        schedulerManager.runNowOnce("PrintOnceTask");


    }
}

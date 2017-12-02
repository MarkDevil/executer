package com.mark.test.framework.util;


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
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SchedulerManager.class);

    static {

        try {
            gSchedulerFactory = new StdSchedulerFactory();
            scheduler = gSchedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定job实现类创建jobdetail对象
     * @param clz   job实现类
     * @return
     */
    private JobDetail getJobDetail(Class<? extends Job> clz){
        String jobname = clz.getSimpleName();
        logger.info("Create jobDetails successfully : {}",jobname);
        JobDetail newjobDetail = newJob(clz).withIdentity(clz.getSimpleName()).requestRecovery().build();
        if (!newjobDetail.equals("")){
            return newjobDetail;
        }else {
            throw new RuntimeException("Create jobDetails failed");
        }

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
     * @param clz       job实现类
     * @param cronExp   定时任务表达式
     */
    public void addScheJob(Class<? extends Job> clz,String cronExp){
        try {

            scheduler.scheduleJob(this.getJobDetail(clz), this.getCronTrigger(cronExp));
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
     * @param clz       定时任务实现类
     * @param interval  时间间隔
     * @param unitType  时间间隔单位
     */
    public void addScheJob(Class<? extends Job> clz,int interval,DateBuilder.IntervalUnit unitType){
        try {

            scheduler.scheduleJob(this.getJobDetail(clz), this.getIntervalTrigger(interval,unitType));
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
     * @param clz
     */
    public void runNowOnce(Class<? extends Job> clz){
        assert clz != null;
        try {
            scheduler = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = this.getJobDetail(clz);
            logger.info("Job key : {}",jobDetail.getKey().toString());
            scheduler.scheduleJob(jobDetail,TriggerBuilder.newTrigger().startNow().build());
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



//    public static void main(String[] args) {
//
//        final SchedulerManager schedulerManager = new SchedulerManager();
//        schedulerManager.addScheJob(PrintHelloTask.class,"* * * * * ?");
//
//        new Thread(new Runnable() {
//            public void run() {
//                while (true){
//                    try {
//                        List<JobExecutionContext> list = scheduler.getCurrentlyExecutingJobs();
//                        if (list.size()>0){
//                            logger.info("Running jobs is {}",list.get(0).getJobDetail());
//                        }
//                    } catch (SchedulerException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();
//    }
}

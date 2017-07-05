package com.mark.test.framework.utils;

import com.mark.test.framework.utils.task.PrintHelloTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;

import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Mark on 2017/7/3.
 */
public class SchedulerManager implements Job{
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "MARK-GROUP";
    private static String TRIGGER_GROUP_NAME = "MARK-TRIGGER";
    private static Scheduler scheduler;
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SchedulerManager.class);


    /**
     * 指定job实现类创建jobdetail对象
     * @param clz
     * @return
     */
    private JobDetail getJobDetail(Class<? extends Job> clz){
        return newJob(clz).withIdentity(clz.getSimpleName()).requestRecovery().build();
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

    private Trigger getIntervalTrigger(int interval, DateBuilder.IntervalUnit intervalUnit){
        return newTrigger().withSchedule(
                DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onEveryDay().withInterval(interval,intervalUnit)).build();
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

    /**
     * 新增定时任务job
     * @param clz       定时任务实现类
     * @param interval  时间间隔
     * @param unitType  时间间隔单位
     */
    public void addScheJob(Class<? extends Job> clz,int interval,DateBuilder.IntervalUnit unitType){
        try {

            scheduler = gSchedulerFactory.getScheduler();
            scheduler.scheduleJob(this.getJobDetail(clz), this.getIntervalTrigger(interval,unitType));
            scheduler.start();
            if (scheduler.isStarted()){
                logger.info("Job start up : {}",scheduler.getSchedulerName());
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void runNow(Class<? extends Job> clz){
        assert clz != null;
        try {
            scheduler = gSchedulerFactory.getScheduler();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("测试定时任务");
    }

    public static void main(String[] args) {

        final SchedulerManager schedulerManager = new SchedulerManager();
        schedulerManager.addScheJob(SchedulerManager.class,2, DateBuilder.IntervalUnit.SECOND);
        schedulerManager.addScheJob(PrintHelloTask.class,"* * * * * ?");

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        List<JobExecutionContext> list = scheduler.getCurrentlyExecutingJobs();
                        if (list.size()>0){
                            logger.info("Running jobs is {}",list.get(0).getJobDetail());
                        }
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();



    }
}

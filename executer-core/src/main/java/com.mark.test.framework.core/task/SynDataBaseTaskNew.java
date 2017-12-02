package com.mark.test.framework.core.task;

import com.mark.test.framework.api.dto.DbCompareRequestDto;
import com.mark.test.framework.core.service.impl.DbCompareImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mark .
 * Data   : 2017/11/30
 * Author : mark
 * Desc   :
 * @author mark
 */
@Component
public class SynDataBaseTaskNew {
    private static Logger logger = LoggerFactory.getLogger(SynDataBaseTaskNew.class);
    private static JobListener jobListener;
    private static DbCompareRequestDto dbCompareRequestDto;

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    DbCompareImpl dbCompare;

    @PostConstruct
    public void initListener() throws SchedulerException {
        jobListener = new JobListener() {
            final String LISTENER = "Monitor";
            @Override
            public String getName() {
                return LISTENER;
            }

            @Override
            public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
                logger.info("Job ************ " + jobExecutionContext.getJobDetail().getKey().getName() + " is going to run");
            }

            @Override
            public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

            }

            @Override
            public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
                logger.info("Job ********** " + jobExecutionContext.getJobDetail().getKey().getName() + " is running");
            }
        };
        schedulerFactoryBean.getScheduler().getListenerManager().addJobListener(jobListener);
    }



    public void run() {
        logger.info("执行数据库同步定时任务");
        dbCompare.compareDb(dbCompareRequestDto);

    }
}

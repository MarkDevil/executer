package com.mark.test.framework.core.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mark .
 * Data : 2017/3/16
 * Desc :
 */
public class PrintLogScheduleTask implements Job {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static String _prefix = "mark-test";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.printName();
    }

    public void printName(){
        long nowtime = System.currentTimeMillis();
        logger.info(_prefix + String.valueOf(nowtime));
    }
}

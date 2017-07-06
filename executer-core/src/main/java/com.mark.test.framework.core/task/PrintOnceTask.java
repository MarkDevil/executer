package com.mark.test.framework.core.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MingfengMa .
 * Data   : 2017/7/6
 * Author : mark
 * Desc   :
 */

public class PrintOnceTask implements Job{
    Logger logger = LoggerFactory.getLogger(PrintOnceTask.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("执行一次任务");
    }
}

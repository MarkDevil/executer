package com.mark.test.framework.utils.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

/**
 * Created by Mark on 2017/7/5.
 */
public class PrintHelloTask implements Job {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(PrintHelloTask.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("hello ,this is {}", jobExecutionContext.getJobDetail().getKey().toString());
    }
}

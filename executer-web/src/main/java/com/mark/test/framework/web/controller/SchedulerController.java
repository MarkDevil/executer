package com.mark.test.framework.web.controller;

import com.mark.test.framework.core.task.PrintLogScheduleTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by MingfengMa .
 * Data : 2017/3/16
 * Desc :
 */
@Controller
@RequestMapping(value = "/api")
public class SchedulerController {
    Logger logger = LoggerFactory.getLogger(SchedulerController.class);
    private PrintLogScheduleTask printLogScheduleTask = new PrintLogScheduleTask();

    @RequestMapping(value = "/schedule",method = RequestMethod.GET)
    @ResponseBody
    public String invokeSchedule() {
        logger.info("Start invoke task ...");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(PrintLogScheduleTask.class).
                    withIdentity("testTask").build();
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "start successfully";
    }

    @RequestMapping(value = "/schedule/onetime",method = RequestMethod.GET)
    @ResponseBody
    public String invoke1time(){
        printLogScheduleTask.printName();
        return "ok";
    }



}

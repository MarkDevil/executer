package com.mark.test.framework.web.controller;

import com.mark.test.framework.core.task.PrintLogScheduleTask;
import com.mark.test.framework.core.task.PrintOnceTask;
import com.mark.test.framework.utils.SchedulerManager;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public Map invoke1time(){
        final SchedulerManager schedulerManager = new SchedulerManager();
        schedulerManager.runNowOnce(PrintOnceTask.class);
//        JSONObject ret = new JSONObject();
        Map<String,ArrayList<String[]>> retmap= new HashMap<>();
        ArrayList<String[]> arrayList = new ArrayList<>();
        arrayList.add(new String[]{"mark","test"});
        arrayList.add(new String[]{"jack","test"});
        retmap.put("data",arrayList);
        logger.info("Response data : {}",retmap.get("data").toString());
//        ret.put("retmsg","ok");
//        ret.put("retcode","00");

        return retmap;
    }

    public static void main(String[] args) {
        new SchedulerController().invoke1time();
    }


}

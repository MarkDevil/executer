package com.mark.test.framework.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.api.dto.ScheduleRequestDto;
import com.mark.test.framework.core.task.PrintLogScheduleTask;
import com.mark.test.framework.core.task.SynDataBaseTask;
import com.mark.test.framework.core.utils.SchedulerManager;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.testng.collections.Maps;

import java.util.Map;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by mark .
 * Data : 2017/3/16
 * Desc :
 *
 * @author mark
 */
@Controller
@RequestMapping(value = "/api")
public class SchedulerController {
    private Logger logger = LoggerFactory.getLogger(SchedulerController.class);

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    @ResponseBody
    public String invokeSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        logger.info("Start invoke task ... , 请求参数为 ：" + scheduleRequestDto);
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(PrintLogScheduleTask.class).
                    withIdentity(scheduleRequestDto.getIdentify()).build();
            Trigger trigger = newTrigger()
                    .withIdentity(scheduleRequestDto.getTriggerName(), scheduleRequestDto.getTriggerGroup())
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "start successfully";
    }

    @RequestMapping(value = "/schedule/onetime", method = RequestMethod.GET)
    @ResponseBody
    public Map invoke1time() {
        final SchedulerManager schedulerManager = new SchedulerManager();
        schedulerManager.runNowOnce("PrintOnceTask");
        JSONObject ret = new JSONObject();
        ret.put("retmsg", "ok");
        ret.put("retcode", "00");
        return ret;
    }

    @RequestMapping(value = "/schedule/sysData", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView sysData() {
        Map<String, Object> ret = Maps.newHashMap();
        ModelAndView view;
        try {
            SynDataBaseTask synDataBaseTask = new SynDataBaseTask();
            synDataBaseTask.run();
        } catch (Exception ex) {
            ret.put("operatid", "同步数据");
            view = new ModelAndView("failed", ret);
            return view;
        }
        ret.put("operatid", "同步数据");
        view = new ModelAndView("Successfully", ret);
        return view;

    }

    public static void main(String[] args) {
        new SchedulerController().invoke1time();
    }


}

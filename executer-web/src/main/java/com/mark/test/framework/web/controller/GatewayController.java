package com.mark.test.framework.web.controller;


import com.mark.test.framework.core.service.impl.GatewayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
@Controller
@RequestMapping(value = "/api")
public class GatewayController {

    private static Logger logger = LogManager.getLogger("HelloWorld");

    @Autowired
    private GatewayService gwService;

    @RequestMapping(method = RequestMethod.POST,value = "/updateGatewayStatus")
    @ResponseBody
    public String updateGatewayStatus(){
        logger.info("updateGatewayStatus page");
        return "hello";
    }


    @RequestMapping(method = RequestMethod.GET,value = "/updatePaymentOrder")
    @ResponseBody
    public String updatePaymentOrder(){
        logger.info("updatePaymentOrder page");
        return "hello";
    }


    @RequestMapping(method = RequestMethod.GET,value = "/queryPage")
    @ResponseBody
    public String queryGwTransfers(){
        logger.info("query page");
        return String.valueOf(gwService.selectByPrimaryKey((long)854));
    }


}

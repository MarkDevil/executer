package com.mark.test.framework.web.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


}

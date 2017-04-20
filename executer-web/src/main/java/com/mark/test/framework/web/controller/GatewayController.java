package com.mark.test.framework.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.core.dto.TestRequestDto;
import com.mark.test.framework.core.service.GwTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
@Controller
@RequestMapping(value = "/api")
public class GatewayController {

    private static Logger logger = LoggerFactory.getLogger("test");

    @Autowired(required = false)
    private GwTransferService queryGwTransfer;

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
        return "ok";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/test")
    @ResponseBody
    public JSONObject getTestMethod(@RequestBody @Valid TestRequestDto testRequestDto){
        logger.info("Request parameter : {}",testRequestDto.toString());
        if (testRequestDto.getName().contains("mark")){
            JSONObject jsonObject = new JSONObject(true);
            jsonObject.put("1","mark");
            jsonObject.put("2","jack");
            return jsonObject;
        }else {
            return null;
        }

    }




}

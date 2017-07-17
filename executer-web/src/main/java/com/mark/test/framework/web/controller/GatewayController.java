package com.mark.test.framework.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.api.dto.TestRequestDto;
import com.mark.test.framework.core.constat.BaseInfo;
import com.mark.test.framework.core.service.GwTransferService;
import com.mark.test.framework.core.service.IBindChargeCard;
import com.mark.test.framework.utils.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
@Controller
@RequestMapping(value = "/api")
public class GatewayController {

    private static Logger logger = LoggerFactory.getLogger(GatewayController.class);
    private BaseInfo baseInfo = new BaseInfo();

    @Autowired(required = false)
    private GwTransferService queryGwTransfer;

    @Autowired
    private IBindChargeCard bindChargeCard;



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

    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    @ResponseBody
    public String generateSign(Map requestmap){
        logger.info("Request parameter : {}",requestmap.toString());
        String sign = null;
        try {
            sign = SignUtils.sign(requestmap, baseInfo.getSecret());
        } catch (Throwable throwable) {
            logger.error("[sign] generate sign failed ...");
            throwable.printStackTrace();
        }
        return sign;
    }


    @RequestMapping(method = RequestMethod.POST,value = "/BindChargeCard")
    @ResponseBody
    public ModelAndView bindChargeCard(@RequestParam Map request){

        logger.info("request parameter : {}",request);
        if (request.get("applyno") == null || request.get("bankno") == null){
            throw new IllegalArgumentException("invalid parameter");
        }
        String applyno = String.valueOf(request.get("applyno"));
        String bankno = String.valueOf(request.get("bankno"));
        try {
             bindChargeCard.bindChargeCard(applyno,bankno);
        }catch (Exception ex){
            logger.error("\n 绑卡失败: \n {},{}", ex.getMessage(), Arrays.toString(ex.getStackTrace()));
            return new ModelAndView("failed","operatid",ex.getMessage());
        }
        return new ModelAndView("Successfully");

    }

    @RequestMapping(method = RequestMethod.POST,value="/updateCardStatus")
    @ResponseBody
    public String updateCardStatus(@RequestParam Map request){
        logger.info("request parameter : {}",request);
        JSONObject ret = new JSONObject();
        String applyNo = request.get("applyNo").toString();
        assert applyNo != null;
        try {
            bindChargeCard.updateBankCardStatus("2",applyNo);
        }catch (Exception ex){
            logger.error("\n 更新卡失败: \n {}",ex);
            ret.put("retcode","failed");
            return ret.toJSONString();
        }
        ret.put("retcode","successfully");
        return ret.toJSONString();
    }


}

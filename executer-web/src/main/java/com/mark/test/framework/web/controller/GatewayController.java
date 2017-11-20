package com.mark.test.framework.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.api.dto.CreateAccDto;
import com.mark.test.framework.api.dto.TestRequestDto;
import com.mark.test.framework.core.constat.BaseInfo;
import com.mark.test.framework.core.service.GwTransferService;
import com.mark.test.framework.core.service.IBindChargeCard;
import com.mark.test.framework.core.service.ICreateAccount;
import com.mark.test.framework.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
@Controller
@RequestMapping(value = "/api")
public class GatewayController {

    private static Logger logger = LoggerFactory.getLogger(GatewayController.class);
    private BaseInfo baseInfo = new BaseInfo();

    private final GwTransferService queryGwTransfer;

    private final IBindChargeCard bindChargeCard;

    private final ICreateAccount createAccount;

    @Autowired(required = false)
    public GatewayController(GwTransferService queryGwTransfer, IBindChargeCard bindChargeCard, ICreateAccount createAccount) {
        this.queryGwTransfer = queryGwTransfer;
        this.bindChargeCard = bindChargeCard;
        this.createAccount = createAccount;
    }


    @RequestMapping(method = RequestMethod.GET,value = "/testList")
    @ResponseBody
    public ModelAndView testlist(){
        List<TestRequestDto> retlist= Lists.newArrayList();
        Map<String,Object> jsonObject = Maps.newLinkedHashMap();
        jsonObject.put("1","mark");
        jsonObject.put("2","jack");
        Map<String,Object> jsonObject1 = Maps.newLinkedHashMap();
        jsonObject1.put("3","lucy");
        jsonObject1.put("4","mock");
        for (Map.Entry entry:jsonObject.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            TestRequestDto requestDto = new TestRequestDto();
            requestDto.setId(key);
            requestDto.setName(value);
            retlist.add(requestDto);
        }
        ModelAndView modelAndView = new ModelAndView("showList");
        modelAndView.addObject("retlist",retlist);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/testDataList")
    @ResponseBody
    public List<TestRequestDto> getBootstrapdata(){
        List<TestRequestDto> retlist= Lists.newArrayList();
        Map<String,Object> jsonObject = Maps.newLinkedHashMap();
        jsonObject.put("1","mark");
        jsonObject.put("2","jack");
        for (Map.Entry entry:jsonObject.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            TestRequestDto requestDto = new TestRequestDto();
            requestDto.setId(key);
            requestDto.setName(value);
            retlist.add(requestDto);
        }
        return retlist;
    }


    @RequestMapping(method = RequestMethod.GET,value = "/testData")
    @ResponseBody
    public JSONObject getTestData(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","mark");
        jsonObject.put("2","jack");

        return jsonObject;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/testDataPara")
    @ResponseBody
    public JSONObject getTestDataPara(@RequestParam String id,@RequestParam String name){
        logger.info("id is {} \t name is {}",id,name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","mark");
        jsonObject.put("2","jack");
        return jsonObject;
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

    @RequestMapping(method = RequestMethod.POST,value = "/createAccInfo")
    @ResponseBody
    public String createAcc(CreateAccDto createAccDto){
        logger.info("Request paramenter : {}", createAccDto);
        JSONObject ret = new JSONObject();
        try {
            createAccount.createAcc(createAccDto);
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("Create account failed");
            ret.put("retcode","failed");
            return ret.toJSONString();
        }
        ret.put("retcode","successfully");
        return ret.toJSONString();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/startBatchCreateAcc")
    @ResponseBody
    public String startBatchCreateAcc(){
        boolean flag = createAccount.startBatch();
        if (flag){
            return "执行成功".toString();
        }else {
            return "执行失败".toString();
        }
    }
}

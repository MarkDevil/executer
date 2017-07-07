package com.mark.test.framework.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.web.testng.RunTestCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MingfengMa .
 * Data   : 2017/3/28
 * Author : mark
 * Desc   :
 */
@Controller
@RequestMapping(value = "/api")
public class AmsIntfController {

//    Logger logger = LoggerFactory.getLogger(AmsIntfController.class);
    RunTestCase runTestCase = new RunTestCase();

    @RequestMapping(value = "/ams/hireHourse",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject hirehourse(){
        runTestCase.run("testcase");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("retCode","200");
        return jsonObject;
    }

    @RequestMapping(value = "/test/view" ,method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView testView(){
        return new ModelAndView("Successfully");
    }
}

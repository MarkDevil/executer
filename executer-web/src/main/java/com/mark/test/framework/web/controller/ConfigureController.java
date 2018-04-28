package com.mark.test.framework.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.api.dto.conf.ConfigRequestDto;
import com.mark.test.framework.core.service.impl.AbstractConfigureServiceImpl;
import com.mark.test.framework.core.service.impl.ConfigureServiceImpl;
import com.mark.test.framework.util.loaddata.ProptiesReader;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2018/2/9
 *
 * @Author : mark
 * Desc   :
 */
@Controller
@RequestMapping(value = "/conf")
public class ConfigureController {

    private Logger logger = LoggerFactory.getLogger(ConfigureController.class);

    @Qualifier("configureServiceImpl")
    @Autowired
    private ConfigureServiceImpl configureService;

    @Qualifier("abstractConfigureServiceImpl")
    @Autowired
    private AbstractConfigureServiceImpl abstractConfigureService;


    @RequestMapping(value = "/readConf",method = RequestMethod.POST)
    @ResponseBody
    public List<Object> readConf(@RequestBody @NotBlank ConfigRequestDto configRequestDto){
        logger.info("请求参数为：{}",configRequestDto.toString());
        String filePath = configRequestDto.getFilePath();
        String fileName = configRequestDto.getFileName();
        Object[] retobj = abstractConfigureService.getProperties(filePath,fileName);
        return Arrays.asList(retobj);
    }


    @RequestMapping(value = "/setValue",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setValue(@RequestBody @NotBlank ConfigRequestDto configRequestDto){
        logger.info("请求参数为：{}",configRequestDto.toString());
        abstractConfigureService.setPropertyValue(
                configRequestDto.getFilePath(),
                configRequestDto.getFileName(),
                configRequestDto.getKey(),
                configRequestDto.getNewValue()
                );
        return (JSONObject) new JSONObject().put("ret","ok");
    }


    @RequestMapping(value = "/set",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject set(@RequestBody ConfigRequestDto configRequestDto){
        logger.info("请求参数为：{}",configRequestDto.toString());
        Map<String,Object> kv = new HashMap<>();
        kv.put(configRequestDto.getKey(),configRequestDto.getNewValue());
        try {
            configureService.setProperty(
                    new File(configRequestDto.getFilePath() + configRequestDto.getFileName()),kv);
        }catch (Exception ex){
            ex.printStackTrace();
            return (JSONObject) new JSONObject().put("ret","failed");
        }
        return (JSONObject) new JSONObject().put("ret","ok");
    }

    @RequestMapping(value = "/reload",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject reload(@RequestBody ConfigRequestDto configRequestDto){
        logger.info("请求参数为：{}",configRequestDto.toString());
//        Map<String,Object> kv = new HashMap<>();
        ProptiesReader proptiesReader = new ProptiesReader(configRequestDto.getFilePath(),configRequestDto.getFileName());
        proptiesReader.loadData();
        return (JSONObject) new JSONObject().put("ret","ok");
    }


}

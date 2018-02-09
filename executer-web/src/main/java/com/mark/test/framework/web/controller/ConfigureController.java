package com.mark.test.framework.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.api.dto.conf.ConfigRequestDto;
import com.mark.test.framework.core.service.IConfigureService;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private IConfigureService iConfigureService;

    @RequestMapping(value = "/readConf",method = RequestMethod.POST)
    @ResponseBody
    public List<Object> readConf(@RequestBody @NotBlank ConfigRequestDto configRequestDto){
        logger.info("请求参数为：{}",configRequestDto.toString());
        String filePath = configRequestDto.getFilePath();
        String fileName = configRequestDto.getFileName();
        Object[] retobj = iConfigureService.getProperties(filePath,fileName);
        return Arrays.asList(retobj);
    }


    @RequestMapping(value = "/setValue",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject setValue(@RequestBody @NotBlank ConfigRequestDto configRequestDto){
        logger.info("请求参数为：{}",configRequestDto.toString());
        iConfigureService.setPropertyValue(
                configRequestDto.getFilePath(),
                configRequestDto.getFileName(),
                configRequestDto.getKey(),
                configRequestDto.getNewValue()
                );
        return (JSONObject) new JSONObject().put("ret","ok");
    }
}

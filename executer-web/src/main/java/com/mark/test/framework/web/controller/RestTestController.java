package com.mark.test.framework.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mark.test.framework.api.dto.conf.ConfigRequestDto;
import com.mark.test.framework.core.dto.comm.CommRestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mark .
 * Data   : 2018/2/9
 *
 * @Author : mark
 * Desc   :
 */
@RestController
@RequestMapping(value = "/conf")
public class RestTestController {

    private Logger logger = LoggerFactory.getLogger(RestTestController.class);


    @RequestMapping(value = "/restTest",method = RequestMethod.POST)
    public Object reload(@RequestBody ConfigRequestDto configRequestDto){
        CommRestDto commRestDto = new CommRestDto();
        logger.info("请求参数为：{}",configRequestDto.toString());
        commRestDto.setCode("0");
        commRestDto.setMsg("成功");
        commRestDto.setData("");
        logger.info("返回信息：{}",commRestDto.toString());
        return commRestDto;
    }


}

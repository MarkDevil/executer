package com.mark.test.framework.web.controller;

import com.mark.test.framework.core.service.IDbExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/7/27
 * Author : mark
 * Desc   :
 */
@Controller
@RequestMapping("/db")
public class DbExecuterController {
    Logger logger = LoggerFactory.getLogger(DbExecuterController.class);

    @Autowired
    private IDbExecuter iDbExecuter;

    @RequestMapping(value = "/exec")
    @ResponseBody
    public List executerDb(@RequestParam Map reqPara){
        List<Map<String,Object>> ret;
        logger.info("请求参数为: {},{}",reqPara.toString(),reqPara.get("sql"));
        iDbExecuter.runsql(reqPara,reqPara.get("sql").toString());
        ret = iDbExecuter.query(reqPara,reqPara.get("sql").toString());
        return ret;
    }
}

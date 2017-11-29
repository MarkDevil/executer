package com.mark.test.framework.web.controller;

import com.mark.test.framework.api.dto.DbCompareRequestDto;
import com.mark.test.framework.core.service.IDbExecuter;
import com.mark.test.framework.core.service.impl.DbCompareImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private Logger logger = LoggerFactory.getLogger(DbExecuterController.class);

    private final DbCompareImpl dbCompare;

    private final IDbExecuter iDbExecuter;

    @Autowired
    public DbExecuterController(DbCompareImpl dbCompare, IDbExecuter iDbExecuter) {
        this.dbCompare = dbCompare;
        this.iDbExecuter = iDbExecuter;
    }

    @RequestMapping(value = "/exec",method = RequestMethod.POST)
    @ResponseBody
    public List executerDb(@RequestParam Map reqPara){
        List<Map<String,Object>> ret;
        logger.info("请求参数为: {} \n 执行sql为:{}",reqPara.toString(),reqPara.get("sql"));
        iDbExecuter.runsql(reqPara,reqPara.get("sql").toString());
        ret = iDbExecuter.query(reqPara,reqPara.get("sql").toString());
        logger.info("执行sql返回结果为：{}",ret);
        return ret;
    }

    @RequestMapping(value = "/compare",method = RequestMethod.POST)
    @ResponseBody
    public List<String> compareTable(DbCompareRequestDto dbCompareRequestDto){
        return dbCompare.compareDb(dbCompareRequestDto);
    }
}

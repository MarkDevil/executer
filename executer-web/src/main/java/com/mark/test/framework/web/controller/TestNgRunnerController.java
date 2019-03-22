package com.mark.test.framework.web.controller;


import com.mark.test.framework.core.dto.comm.CommRestDto;
import com.mark.test.framework.web.testng.RunTestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mark
 */
@Slf4j
@RestController
@RequestMapping("testng")
public class TestNgRunnerController {

    @Autowired
    private RunTestCase runTestCase;

    @RequestMapping("run")
    public CommRestDto runTest(){
        CommRestDto commRestDto = new CommRestDto();
        boolean flag = runTestCase.run("testcase.xml");
        if (flag){
            commRestDto.setCode("0");
            return commRestDto;
        }else {
            commRestDto.setCode("1");
            return commRestDto;
        }
    }
}

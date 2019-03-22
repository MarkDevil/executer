package com.mark.test.framework.web.testng;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.testng.IReporter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark .
 * Data   : 2017/3/28
 * Author : mark
 * Desc   :
 */
@Slf4j
@Component
public class RunTestCase {
    private TestNG testNG = new TestNG(true);
    private TestListenerAdapter testListenerAdapter = new TestListenerAdapter();

    public boolean run(String xmlname){
        try {
            testNG.setXmlPathInJar(this.getClass().getClassLoader().getResource("testSuits/".concat(xmlname)).getPath());
            testNG.addListener(testListenerAdapter);
            testNG.run();

            for (IReporter reporter : testNG.getReporters()) {
                log.info("ireporter : " + reporter);
            }

            log.info("\n" + "成功:" + testListenerAdapter.getPassedTests() + "\t" +
                    "\n" + "失败:" + testListenerAdapter.getFailedTests() + "\t" +
                    "\n" + "跳过:" + testListenerAdapter.getSkippedTests() + "\t");
            return true;
        }catch (NullPointerException ex){
            log.error(ex + "TestNg file没找到");
            return false;
        }

    }

    public static void main(String[] args) {
        new RunTestCase().run("testcase");
    }
}

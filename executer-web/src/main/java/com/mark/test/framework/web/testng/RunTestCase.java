package com.mark.test.framework.web.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingfengMa .
 * Data   : 2017/3/28
 * Author : mark
 * Desc   :
 */
public class RunTestCase {
    static Logger logger = LoggerFactory.getLogger(RunTestCase.class);
    TestNG testNG = new TestNG(true);
    TestListenerAdapter testListenerAdapter = new TestListenerAdapter();

    public void run(String xmlname){
        List<String> suites = new ArrayList<String>();
        suites.add(this.getClass().getClassLoader().getResource("testSuits").getPath()+ xmlname + ".xml");
        testNG.setTestSuites(suites);
        testNG.addListener(testListenerAdapter);
        testNG.run();

        for (IReporter reporter : testNG.getReporters()) {
            logger.info("ireporter : " + reporter);
        }
        logger.info("\n" + "成功:" + testListenerAdapter.getPassedTests() + "\t" +
                    "\n" + "失败:" + testListenerAdapter.getFailedTests() + "\t" +
                    "\n" + "跳过:" + testListenerAdapter.getSkippedTests() + "\t");
    }

    public static void main(String[] args) {
        new RunTestCase().run("testcase");
    }
}

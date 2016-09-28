package com.mark.test.framework.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
public class Hello {
    private static Logger logger = LogManager.getLogger("HelloWorld");
    public static void main(String[] args) {
        logger.info("测试");
        logger.error("错误");

    }
}

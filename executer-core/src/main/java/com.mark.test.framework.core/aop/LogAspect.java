package com.mark.test.framework.core.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mark .
 * Data   : 2017/12/25
 * Author : mark
 * Desc   :
 */
@Aspect
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut(value = "execution(* *..service..*.*(..))")
    public void aspect(){}

    @After("aspect()")
    public void afterPointcut(){
        logger.info("point cut finished");
    }

    @Before("aspect()")
    public void beforePointcut(){
        logger.info("beforePointcut");
    }


}

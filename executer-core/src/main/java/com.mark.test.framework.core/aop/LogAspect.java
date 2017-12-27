package com.mark.test.framework.core.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by mark .
 * Data   : 2017/12/25
 * @author : mark
 * Desc   :
 */
@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut(value = "execution(* *..service..*.*(..))")
    public void aspect(){}

    @After("aspect()")
    public void afterPointcut(){
        logger.info("Point cut finished");
    }

    @Before("aspect()")
    public void beforePointcut(){
        logger.info("Before Pointcut");
    }


}

package com.mark.test.framework.web.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
public class ExecuterTimeInterceptor extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(ExecuterTimeInterceptor.class);

    private static final String START_TIME_NAME = "_startTime_";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Date now = new Date();
        request.setAttribute(START_TIME_NAME, now.getTime());
        logger.info("Received request at {}", new Object[] {  now });
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        try {
            long startTime = new Date().getTime();
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("{} execution time: {}", new Object[] { request.getRequestURI(), elapsedTime });
        }
        catch (Exception ignore) {
            logger.error("Failed to calc controller elapsed time", ignore);
        }
    }
}

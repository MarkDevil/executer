package com.mark.test.framework.web.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by mark .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
public class ExecuterTimeInterceptor extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(ExecuterTimeInterceptor.class);

    private static final String START_TIME_NAME = "_startTime_";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Date now = new Date();
        request.setAttribute(START_TIME_NAME, now.getTime());
        String realip = request.getRemoteAddr();
        logger.info("\n Received request at: \t {} \n [Remote ip] : \t{}", new Object[] {  now ,realip});
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        try {
            long startTime = (Long) request.getAttribute(START_TIME_NAME);
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("{} execution time: {} ms ", new Object[] { request.getRequestURI(), elapsedTime });
        }
        catch (Exception ignore) {
            logger.error("Failed to calc controller elapsed time", ignore);
        }
    }
}

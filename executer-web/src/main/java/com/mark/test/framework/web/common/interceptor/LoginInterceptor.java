package com.mark.test.framework.web.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mark .
 * Data   : 2018/3/5
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (url.contains("tologin")){
            return true;
        }
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        logger.debug("Session中获取到的客户名为:{}",username);
        if (username!= null){
            return true;
        }
        request.getRequestDispatcher("/tologin").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

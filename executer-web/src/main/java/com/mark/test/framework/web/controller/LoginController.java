package com.mark.test.framework.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mark on 2017/7/9.
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("navPage");
    }

    @RequestMapping(value = "/nav")
    @ResponseBody
    public ModelAndView nav(){
        return new ModelAndView("navPage");
    }

    @RequestMapping(value = "/monitor")
    @ResponseBody
    public ModelAndView monitor(){
        return new ModelAndView("monitor");
    }
}

package com.mark.test.framework.web.controller;

import com.mark.test.framework.api.dto.UserDto;
import com.mark.test.framework.core.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mark on 2017/7/9.
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);


    private final UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
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


    @RequestMapping(value = "/User")
    @ResponseBody
    public String user(@RequestParam String name){
        UserDto user = userService.getUser(name);
        if (user != null){
            return String.format("User %s is exsit",user.getName());
        }else {
            return "User is not exsit";
        }
    }
}

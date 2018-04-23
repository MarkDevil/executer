package com.mark.test.framework.web.controller;

import com.mark.test.framework.api.dto.UserDto;
import com.mark.test.framework.core.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Mark on 2017/7/9.
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = {"/tologin","/"})
    @ResponseBody
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/home")
    @ResponseBody
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/nav")
    @ResponseBody
    public ModelAndView nav(){
        return new ModelAndView("navpage");
    }

    @RequestMapping(value = "/monitor")
    @ResponseBody
    public ModelAndView monitor(){
        return new ModelAndView("monitor");
    }

    @RequestMapping(value = "/api")
    @ResponseBody
    public ModelAndView api(){
        return new ModelAndView("api");
    }


    @RequestMapping(value = "/dbcompare")
    @ResponseBody
    public ModelAndView dbcompare(){
        return new ModelAndView("dbcompare");
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String user(@RequestParam Map request , HttpSession session){
        logger.info("请求信息：{}",request.toString());
        String username = (String) request.get("userName");
        UserDto user = userService.getUser(username);
        if (user != null){
            session.setAttribute("username",username);
            return "yes";
        }else {
            return "redirect:/tologin";
        }
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public String user(HttpSession session){
        //清除Session
        session.invalidate();
        return "redirect:/tologin";
    }

}

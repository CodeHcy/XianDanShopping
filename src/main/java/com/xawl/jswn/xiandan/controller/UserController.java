package com.xawl.jswn.xiandan.controller;

import com.xawl.jswn.xiandan.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired()
    private Test user;

    @RequestMapping("/user.do")
    @ResponseBody
    public ModelAndView user(){
        String userName = user.getUserName();
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",userName);
        mv.setViewName("user");
        return mv;
    }
}

package com.xiaoyang.event.controller;  

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountPageController {
	
    //登录
    @RequestMapping("/login")
  	public ModelAndView login() {
    	return new ModelAndView("login");
  	}
    
    //登出
	@RequestMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
    	httpSession.invalidate();
    	return new ModelAndView("redirect:/login");
    }
}
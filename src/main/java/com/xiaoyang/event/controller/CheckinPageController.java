package com.xiaoyang.event.controller;  

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/checkin")
public class CheckinPageController {
	
    @RequestMapping("/list")
  	public ModelAndView list(int userId) {
    	ModelAndView mv = new ModelAndView("checkin");
    	mv.addObject("userId", userId);
    	return mv;
  	}
}
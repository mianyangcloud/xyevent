package com.xiaoyang.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.service.UserService;

@Controller
@RequestMapping("/user")
public class UserPageController extends BaseCotroller{
	
	@Autowired
	UserService userService;
	
    @RequestMapping("/list")
  	public ModelAndView list() {
    	return new ModelAndView("user");
  	}
    
    @PostMapping("/edit")
  	public ModelAndView edit(User user) {
    	ModelAndView mv = new ModelAndView("redirect:/user/list");
    	user.setEventId(getEvent().getId());
    	if(user.getId() == null) {
    		userService.add(user);
    	} else {
    		userService.update(user);
    	}
    	return mv;
  	}
    
    @RequestMapping("/delete")
    public ModelAndView delete(int id) {
    	ModelAndView mv = new ModelAndView("redirect:/user/list");
    	userService.delete(id);
    	return mv;
    }
}
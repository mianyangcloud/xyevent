package com.xiaoyang.event.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupPageController extends BaseCotroller{
	
	@Autowired
	GroupService groupService;
	
    //组别列表
    @RequestMapping("/list")
  	public ModelAndView list(int type) {
    	ModelAndView mv = new ModelAndView("group");
    	mv.addObject("type", type);
    	return mv;
  	}
    
    //添加组别
    @PostMapping("/edit")
  	public ModelAndView edit(Group group) {
    	ModelAndView mv = new ModelAndView("group");
    	mv.addObject("type", group.getType());
    	group.setEventId(getEvent().getId());
    	if(group.getId() == null) {
    		groupService.add(group);
    	} else {
    		groupService.update(group);
    	}
    	return mv;
  	}
    
    //添加组别
    @RequestMapping("/delete")
  	public ModelAndView delete(int groupId, int type) {
    	ModelAndView mv = new ModelAndView("group");
    	mv.addObject("type", type);
    	groupService.delete(groupId);
    	return mv;
  	}
}
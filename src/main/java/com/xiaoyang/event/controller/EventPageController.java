package com.xiaoyang.event.controller;  

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.event.domain.Event;
import com.xiaoyang.event.dto.EventDto;
import com.xiaoyang.event.service.EventService;

@Controller
@RequestMapping("/event")
public class EventPageController {
	
	@Autowired
	EventService eventService;
	
    //活动列表
    @RequestMapping("/list")
  	public ModelAndView list() {
    	return new ModelAndView("event");
  	}
    
    //活动列表
    @PostMapping("/edit")
  	public ModelAndView edit(EventDto eventDto) {
    	ModelAndView mv = new ModelAndView("event");
    	if(eventDto.getId() == null) {
    		eventService.add(eventDto.convertToEvent());
    	} else {
    		eventService.update(eventDto.convertToEvent());
    	}
    	return mv;
  	}
    
    //管理活动
    @RequestMapping("/management")
  	public ModelAndView management(int eventId, HttpSession httpSession) {
    	Event event = eventService.findById(eventId);
    	httpSession.setAttribute("event", event);
    	return new ModelAndView("redirect:/user/list");
  	}
}
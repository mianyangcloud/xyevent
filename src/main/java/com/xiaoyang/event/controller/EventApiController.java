package com.xiaoyang.event.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventApiController extends BaseCotroller{
	
	@Autowired
	EventService eventService;
	
    //活动列表
    @RequestMapping("/list")
  	public PageDto list(PageModel pageModel, String searchText) {
    	return eventService.list(getAccount().getId(), searchText, pageModel);
  	}
}
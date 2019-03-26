package com.xiaoyang.event.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupApiController extends BaseCotroller{
	
	@Autowired
	GroupService groupService;
	
    //组别列表
    @RequestMapping("/list")
  	public PageDto list(PageModel pageModel, int type, String searchText) {
    	return groupService.list(type, getEvent().getId(), searchText, pageModel);
  	}
}
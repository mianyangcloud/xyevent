package com.xiaoyang.event.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.CheckinService;

@RestController
@RequestMapping("/api/checkin")
public class CheckinApiController extends BaseCotroller{
	
	@Autowired
	CheckinService checkinService;
	
    @RequestMapping("/list")
  	public PageDto list(int userId, PageModel pageModel) {
    	return checkinService.list(userId, pageModel);
  	}
}
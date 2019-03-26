package com.xiaoyang.event.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsApiController extends BaseCotroller{
	
	@Autowired
	NewsService newsService;
	
    @RequestMapping("/list")
  	public PageDto list(PageModel pageModel, String searchText) {
    	return newsService.list(getEvent().getId(), searchText, pageModel);
  	}
    
    @RequestMapping("/details")
  	public ResultResp details(Integer newsId) {
    	return ResultResp.returnSuccess(newsService.findById(newsId));
  	}
}
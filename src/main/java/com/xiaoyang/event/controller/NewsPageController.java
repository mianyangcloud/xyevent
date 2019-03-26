package com.xiaoyang.event.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.event.domain.News;
import com.xiaoyang.event.service.NewsService;
import com.xiaoyang.event.utils.ImageUtil;

@Controller
@RequestMapping("/news")
public class NewsPageController extends BaseCotroller{
	
	@Autowired
	NewsService newsService;
	
    @RequestMapping("/list")
  	public ModelAndView list() {
    	return new ModelAndView("news");
  	}
    
    @PostMapping("/edit")
  	public ModelAndView edit(News news, @RequestParam MultipartFile file) {
    	ModelAndView mv = new ModelAndView("redirect:/news/list");
    	news.setEventId(getEvent().getId());
    	if(file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
			news.setImageUrl(ImageUtil.uploadImage(file));
		}
    	if(news.getId() == null) {
    		newsService.add(news);
    	} else {
    		newsService.update(news);
    	}
    	return mv;
  	}
    
    @RequestMapping("/delete")
    public ModelAndView delete(int id) {
    	ModelAndView mv = new ModelAndView("redirect:/news/list");
    	newsService.delete(id);
    	return mv;
    }
}
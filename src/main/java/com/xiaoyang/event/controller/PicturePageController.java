package com.xiaoyang.event.controller;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.service.GroupService;
import com.xiaoyang.event.service.PictureService;

@Controller
@RequestMapping("/picture")
public class PicturePageController extends BaseCotroller{
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	PictureService pictureService;
	
    //精彩图片列表
    @RequestMapping("/list")
  	public ModelAndView list() {
    	List<Group> groupList = groupService.list(0, getEvent().getId(), null);
    	ModelAndView mv = new ModelAndView("picture");
    	mv.addObject("groupList", groupList);
    	return mv;
  	}
    
    //精彩图片列表
    @RequestMapping("/listByUser")
  	public ModelAndView listByUser(int userId) {
    	ModelAndView mv = new ModelAndView("userPicture");
    	mv.addObject("userId", userId);
    	return mv;
  	}
    
    //编辑图片信息
  	@RequestMapping("/edit")
  	public ModelAndView edit(int groupId, String pictureId) {
  		ModelAndView mv = new ModelAndView("redirect:/picture/list");
  		pictureService.update(groupId, pictureId);
  		return mv;
  	}
    
    //删除图片
    @RequestMapping("/delete")
    public ModelAndView delete(String id) {
    	ModelAndView mv = new ModelAndView("redirect:/picture/list");
    	pictureService.delete(id);
    	return mv;
    }
}
package com.xiaoyang.event.controller;  

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.domain.Video;
import com.xiaoyang.event.service.GroupService;
import com.xiaoyang.event.service.VideoService;
import com.xiaoyang.event.utils.ImageUtil;

@Controller
@RequestMapping("/video")
public class VideoPageController extends BaseCotroller{
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	VideoService videoService;
	
    //精彩图片列表
    @RequestMapping("/list")
  	public ModelAndView list() {
    	List<Group> groupList = groupService.list(1, getEvent().getId(), null);
    	ModelAndView mv = new ModelAndView("video");
    	mv.addObject("groupList", groupList);
    	return mv;
  	}
    
    //精彩图片列表
    @RequestMapping("/listByUser")
  	public ModelAndView listByUser(int userId) {
    	ModelAndView mv = new ModelAndView("userVideo");
    	mv.addObject("userId", userId);
    	return mv;
  	}
    
    //编辑视频信息
   	@RequestMapping("/edit")
   	public ModelAndView edit(Video video, MultipartFile imageFile) {
   		ModelAndView mv = new ModelAndView("redirect:/video/list");
   		if(imageFile != null && !StringUtils.isEmpty(imageFile.getOriginalFilename())) {
   			video.setHeadImage(ImageUtil.uploadImage(imageFile));
   		}
   		video = videoService.updateVideoInfo(video);
   		return mv;
   	}
    
    @RequestMapping("/delete")
    public ModelAndView delete(String id) {
    	ModelAndView mv = new ModelAndView("redirect:/video/list");
    	videoService.delete(id);
    	return mv;
    }
}
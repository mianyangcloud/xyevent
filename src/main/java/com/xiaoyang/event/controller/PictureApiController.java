package com.xiaoyang.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.domain.Picture;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.PictureService;
import com.xiaoyang.event.utils.ImageUtil;

@RestController
@RequestMapping("/api/picture")
public class PictureApiController extends BaseCotroller{
	
	@Autowired
	PictureService pictureService;
	
    //上传图片
    @PostMapping("/upload")
  	public ResultResp upload(@RequestParam MultipartFile[] file, Integer groupId) {
    	for(MultipartFile m:file) {
    		Picture picture = Picture.of()
    			.setEventId(getEvent().getId())
    			.setSize(m.getSize())
    			.setTitle(m.getOriginalFilename())
    			.setUrl(ImageUtil.uploadImage(m));
        	if(groupId != null) {
        		picture.setGroupId(groupId);
        	}
        	pictureService.add(picture);
    	}
    	return new ResultResp();
  	}
    
    //图片列表
    @RequestMapping("/list")
    public PageDto list(PageModel pageModel, Integer groupId) {
    	if(groupId == null) {
    		return pictureService.list(getEvent().getId(), pageModel);
    	} else {
    		return pictureService.listByGroupId(groupId, pageModel);
    	}
    }
    
    //图片列表
    @RequestMapping("/listByUserId")
    public PageDto listByUserId(PageModel pageModel, Integer userId) {
    	return pictureService.listByUserId(userId, pageModel);
    }
}
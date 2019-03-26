package com.xiaoyang.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.service.PictureService;
import com.xiaoyang.event.utils.ImageUtil;

@RestController
@RequestMapping("/api/common")
public class CommonApiController extends BaseCotroller{
	
	@Autowired
	PictureService pictureService;
	
    //上传图片
    @PostMapping("/picture/upload")
  	public ResultResp upload(@RequestParam MultipartFile file) {
    	String url = ImageUtil.uploadImage(file);
    	return new ResultResp(CommonCodes.SUCCESS, url);
  	}
}
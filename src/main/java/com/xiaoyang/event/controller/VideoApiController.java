package com.xiaoyang.event.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.domain.Video;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.VideoService;
import com.xiaoyang.event.utils.VodUtil;

@RestController
@RequestMapping("/api/video")
public class VideoApiController extends BaseCotroller{
	
	@Autowired
	VideoService videoService;
    
    //获取点播上传地址并保存视频
    @PostMapping("/getUploadUrl")
	public ResultResp getUploadUrl(Video video) {
    	Map urlMap = VodUtil.getUploadConf(video.getTitle(), video.getFileName());
    	video.setVideoId(urlMap.get("videoId").toString());
    	if(video.getEventId() == null) {
    		video.setEventId(getEvent().getId());
    	}
    	videoService.add(video);
    	return new ResultResp(CommonCodes.SUCCESS, urlMap);
	}
    
    //获取视频信息
	@RequestMapping("/getVideoInfo")
	public ResultResp getVideoInfo(String videoId) {
		return new ResultResp(CommonCodes.SUCCESS, videoService.findByVideoId(videoId));
	}
    
    //视频列表
    @RequestMapping("/list")
    public PageDto list(PageModel pageModel, String searchText, Integer groupId) {
    	if(groupId == null) {
    		return videoService.list(getEvent().getId(), searchText, pageModel);
    	} else {
    		return videoService.listByGroupId(groupId, searchText, pageModel);
    	}
    }
    
    //视频列表
    @RequestMapping("/listByUserId")
    public PageDto listByUserId(PageModel pageModel, String searchText, Integer userId) {
    	return videoService.listByUserId(userId, searchText, pageModel);
    }
}
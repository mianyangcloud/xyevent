package com.xiaoyang.event.controller;  

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.constant.OSS;
import com.xiaoyang.event.domain.Checkin;
import com.xiaoyang.event.domain.Picture;
import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.domain.Video;
import com.xiaoyang.event.dto.NewsDto;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.CheckinService;
import com.xiaoyang.event.service.NewsService;
import com.xiaoyang.event.service.PictureService;
import com.xiaoyang.event.service.SMSService;
import com.xiaoyang.event.service.UserService;
import com.xiaoyang.event.service.VideoService;
import com.xiaoyang.event.utils.ImageUtil;
import com.xiaoyang.event.utils.VodUtil;

@RestController
@RequestMapping("/api/appleTree")
public class AppleTreeApiController extends BaseCotroller{
	
	private int eventId = 4;
	
	private String address = "皇家园林";
	
	@Autowired
	SMSService smsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NewsService newsService;
	
	@Autowired
	CheckinService checkinService;
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	VideoService videoService;
	
	@RequestMapping("/sendCode")
	public ResultResp sendCode(String mobilenum) {
		int code = smsService.sendCode(mobilenum);
		boolean ret = userService.updateCode(eventId, code, mobilenum);
		if(!ret) {
			User user = User.of()
				.setEventId(eventId)
				.setMobilenum(mobilenum)
				.setCode(code);
			userService.add(user);
		}
		return ResultResp.returnSuccess();
	}
	
	@RequestMapping("/login")
	public ResultResp login(String mobilenum, int code) {
		User user = userService.findByMobilenum(eventId, mobilenum);
		if(user == null) {
			return ResultResp.returnError(CommonCodes.ERROR_PARAMS, "手机号不存在");
		}
		if(user.getCode() == null || !user.getCode().equals(code)) {
			return ResultResp.returnError(CommonCodes.ERROR_PARAMS, "验证码错误");
		}
		return ResultResp.returnSuccess(user); 
	}
	
    @RequestMapping("/knowledge/List")
  	public ResultResp knowledgeList(PageModel pageModel) {
    	PageDto pageDto = newsService.list(eventId, null, pageModel);
    	List<NewsDto> list = pageDto.getRows();
    	for(NewsDto newsDto : list) {
    		newsDto.setContent("");
    	}
    	return ResultResp.returnSuccess(pageDto);
  	}
    
    @RequestMapping("/knowledge/details")
  	public ResultResp knowledgeDetails(int knowledgeId) {
    	return ResultResp.returnSuccess(newsService.findById(knowledgeId));
  	}
    
    @RequestMapping("/checkin/add")
  	public ResultResp checkinAdd(int userId, String remark) {
    	User user = userService.findById(userId);
    	if(user == null || StringUtils.isEmpty(remark) || !remark.equals(user.getRemark())) {
    		return ResultResp.returnError(CommonCodes.ERROR_PARAMS, "签到失败，信息不匹配");
    	}
    	Checkin checkin = Checkin.of();
    	checkin.setAddress(address)
    		.setEventId(eventId)
    		.setUserId(userId);
    	checkinService.add(checkin);
    	return ResultResp.returnSuccess();
  	}
    
    @RequestMapping("/checkin/list")
  	public ResultResp checkinList(int userId, PageModel pageModel) {
    	return ResultResp.returnSuccess(checkinService.list(userId, pageModel));
  	}
    
    @PostMapping("/picture/upload")
  	public ResultResp pictureUpload(@RequestParam MultipartFile[] file, int userId) {
    	for(MultipartFile m:file) {
    		Picture picture = Picture.of()
    			.setEventId(eventId)
    			.setSize(m.getSize())
    			.setTitle(m.getOriginalFilename())
    			.setUrl(ImageUtil.uploadImage(m))
    			.setUserId(userId);
        	pictureService.add(picture);
    	}
    	return ResultResp.returnSuccess();
  	}
    
    @RequestMapping("/picture/list")
    public ResultResp pictureList(PageModel pageModel, Integer userId) {
    	return ResultResp.returnSuccess(pictureService.listByUserId(userId, pageModel));
    }
    
    @PostMapping("/video/upload")
	public ResultResp getUploadUrl(String title, String fileName, int userId, @RequestParam MultipartFile file) throws IOException {
    	Video video = Video.of();
    	video.setTitle(title);
    	video.setEventId(eventId);
    	video.setUserId(userId);
    	videoService.add(video);
    	String videoId = VodUtil.videoUploadStream(OSS.ACCESS_KEY_ID, OSS.ACCESS_KEY_SECRET, title, fileName, file.getInputStream(), video.getId());
    	if(!StringUtils.isEmpty(videoId)) {
    		video = videoService.findByVideoId(video.getId(), videoId);
    		return ResultResp.returnSuccess(video);
    	}
    	return ResultResp.returnError(CommonCodes.ERROR_PARAMS, "视频上传失败");
	}
    
    @RequestMapping("/video/uploadSuccess")
	public ResultResp uploadSuccess(HttpServletRequest request) throws IOException {
    	try (BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
               byte[] buffer = new byte[1024];
               int len;
               while ((len = bis.read(buffer)) > 0) {
                   baos.write(buffer, 0, len);
               }
               byte[] bytes = baos.toByteArray();
               System.out.println(new String(bytes));;
           } catch (IOException ex) {
               throw ex;
           }
    	return ResultResp.returnSuccess();
	}
    
    @RequestMapping("/video/list")
    public ResultResp videoList(PageModel pageModel, Integer userId) {
    	return ResultResp.returnSuccess(videoService.listByUserId(userId, null, pageModel));
    }

    //-------user------//
	@RequestMapping("/user/listByName")
	public ResultResp listByName(PageModel pageModel, String searchText) {
		return ResultResp.returnSuccess(userService.listByName(4,searchText,pageModel));

	}

    @RequestMapping("/user/checkRealName")
    public ResultResp checkRealName(Integer userId) {
		return ResultResp.returnSuccess(userService.shouldFillRealName(4,userId));
	}
	@RequestMapping("/user/udpateRealName")
	public ResultResp updateRealName(Integer userId,String name) {
		return ResultResp.returnSuccess(userService.updateRealName(4,userId,name));
	}

}
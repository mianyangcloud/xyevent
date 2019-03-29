package com.xiaoyang.event.controller;  

import com.xiaoyang.event.constant.CommonCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController extends BaseCotroller{
	
	@Autowired
	UserService userService;
	
    //用户列表
    @RequestMapping("/list")
  	public PageDto list(PageModel pageModel, String searchText) {
    	return userService.list(getEvent().getId(), searchText, pageModel);
  	}
    
    @RequestMapping("/findByMobilenum")
  	public ResultResp findByMobilenum(Integer eventId, String mobilenum) {
    	if(eventId == null) {
    		eventId = getEvent().getId();
    	}
    	User user = userService.findByMobilenum(eventId, mobilenum);
    	return ResultResp.returnSuccess(user);
  	}
    
    @RequestMapping("/findByEmail")
  	public ResultResp findByEmail(Integer eventId, String email) {
    	if(eventId == null) {
    		eventId = getEvent().getId();
    	}
    	User user = userService.findByEmail(eventId, email);
    	return ResultResp.returnSuccess(user);
  	}

	@RequestMapping("/updateRealName")
	public ResultResp updateRealName(Integer eventId,Integer userId,String realname) {
    	if (userId == null) {return ResultResp.returnError(CommonCodes.ERROR_PARAMS,"userID为空，更新失败");}
		boolean isSucccess = userService.updateRealName(eventId,userId,realname);
    	if (isSucccess) {
    		return ResultResp.returnSuccess();
		} else {
			return ResultResp.returnError(CommonCodes.ERROR_DB_UPDATE,"更新失败");
		}
    }

}
package com.xiaoyang.event.service;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.dto.PageDto;

public interface UserService extends BaseService<User> {
	
	PageDto list(int eventId, String searchText, PageModel pageModel);
	
	//按用户名搜索
	PageDto listByName(int eventId, String searchText, PageModel pageModel);
	
	void delete(int id);
	
	User findByMobilenum(int eventId, String mobilenum);
	
	User findByEmail(int eventId, String email);
	
	boolean updateCode(int eventId, int code, String mobilenum);
	
	boolean updateMeidaNum(int type, int id);
}

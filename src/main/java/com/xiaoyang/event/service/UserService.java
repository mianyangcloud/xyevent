package com.xiaoyang.event.service;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.dto.PageDto;

public interface UserService extends BaseService<User> {
	
	PageDto list(int eventId, String searchText, PageModel pageModel);
	
	void delete(int id);
	
	User findByMobilenum(int eventId, String mobilenum);
	
	User findByEmail(int eventId, String email);
	
	boolean updateCode(int eventId, int code, String mobilenum);

	//是否需要完善真实姓名 如果 userId 为空 返回 false
//	boolean shouldFillRealName(int userId);

	//更新用户姓名
	boolean updateRealName(int eventId,int userId,String realname);

	//按用户名搜索
	PageDto listByName(int eventId, String name, PageModel pageModel);
}

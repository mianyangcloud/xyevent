package com.xiaoyang.event.dao;

import com.xiaoyang.event.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	List<User> list(int eventId, String searchText);
	
	User findByMobilenum(int eventId, String mobilenum);
	
	User findByEmail(int eventId, String email);
	
	int updateCode(int eventId, int code, String mobilenum);

	int updateRealName(int eventId, int userId, String realname);

	List<User> listByName(int eventId,String searchText);

	String shouldFillRealName(int eventId, int userId);
}

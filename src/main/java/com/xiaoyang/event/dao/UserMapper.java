package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	List<User> list(int eventId, String searchText);
	
	User findByMobilenum(int eventId, String mobilenum);
	
	User findByEmail(int eventId, String email);
	
	int updateCode(int eventId, int code, String mobilenum);
}

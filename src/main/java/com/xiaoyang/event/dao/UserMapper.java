package com.xiaoyang.event.dao;

import com.xiaoyang.event.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>{

	List<User> list(int eventId, String searchText);
	
	List<User> listByName(int eventId, int userId, String searchText);
	
	User findByMobilenum(int eventId, String mobilenum);
	
	User findByEmail(int eventId, String email);
	
	int updateCode(int eventId, int code, String mobilenum);
	
	int updateMeidaNum(int type, int id);
}

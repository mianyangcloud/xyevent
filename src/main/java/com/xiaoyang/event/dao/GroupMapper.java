package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.Group;

@Mapper
public interface GroupMapper extends BaseMapper<Group>{
	
	void delete(int id);
	
	List<Group> list(int type, int eventId, String searchText);
}

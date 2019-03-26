package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.Event;

@Mapper
public interface EventMapper extends BaseMapper<Event>{
	
	List<Event> list(int accountId, String searchText);
}

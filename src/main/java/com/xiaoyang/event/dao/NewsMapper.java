package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.News;

@Mapper
public interface NewsMapper extends BaseMapper<News>{

	List<News> list(int eventId, String searchText);
	
	void delete(int id);
}

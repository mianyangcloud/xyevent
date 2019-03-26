package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiaoyang.event.domain.Video;

@Mapper
public interface VideoMapper extends BaseMapper<Video>{
	
	List<Video> list(int eventId, String searchText);
	
	List<Video> listByGroupId(int groupId, String searchText);
	
	List<Video> listByUserId(int userId, String searchText);
	
	Video findByVideoId(String videoId);
	
	void deleteByArrayId(@Param("array")String[] idArray);
	
	void updateByArrayId(@Param("groupId")Integer groupId, @Param("array")String[] idArray);
}

package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiaoyang.event.domain.Picture;

@Mapper
public interface PictureMapper extends BaseMapper<Picture>{
	
	List<Picture> list(int eventId);
	
	List<Picture> listByGroupId(int groupId);
	
	List<Picture> listByUserId(int userId);
	
	void deleteByArrayId(@Param("array")String[] idArray);
	
	void updateByArrayId(@Param("groupId")Integer groupId, @Param("array")String[] idArray);
}

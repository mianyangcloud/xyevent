package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.Picture;
import com.xiaoyang.event.dto.PageDto;

public interface PictureService extends BaseService<Picture>{
	
	List<Picture> list(int eventId);
	
	List<Picture> listByGroupId(int groupId);
	
	List<Picture> listByUserId(int userId);
	
	PageDto listByUserId(int userId, PageModel pageModel);
	
	PageDto listByGroupId(int groupId, PageModel pageModel);
	
	PageDto list(int eventId, PageModel pageModel);
	
	void update(int groupId, String pictureId);
	
	void delete(String id);
	
	void deleteByArrayId(String[] idArray);
	
	void updateByArrayId(Integer groupId, String[] idArray);
}

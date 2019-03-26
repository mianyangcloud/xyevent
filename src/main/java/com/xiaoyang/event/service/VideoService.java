package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.Video;
import com.xiaoyang.event.dto.PageDto;

public interface VideoService extends BaseService<Video>{
	
	List<Video> list(int eventId, String searchText);

	List<Video> listByGroupId(int groupId, String searchText);
	
	PageDto list(int eventId, String searchText, PageModel pageModel);
	
	PageDto listByGroupId(int groupId, String searchText, PageModel pageModel);
	
	PageDto listByUserId(int userId, String searchText, PageModel pageModel);
	
	Video findByVideoId(int id, String videoId);
	
	Video findByVideoId(String videoId);
	
	Video updateVideoInfo(Video video);
	
	void update(int groupId, String videoId);
	
	void delete(String id);
	
	void deleteByArrayId(String[] idArray);
	
	void updateByArrayId(Integer groupId, String[] idArray);
}

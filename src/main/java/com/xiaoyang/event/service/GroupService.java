package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.dto.PageDto;

public interface GroupService extends BaseService<Group>{

	List<Group> list(int type, int eventId, String searchText);
	
	PageDto list(int type, int eventId, String searchText, PageModel pageModel);
	
	void delete(int groupId);
}

package com.xiaoyang.event.service;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.News;
import com.xiaoyang.event.dto.NewsDto;
import com.xiaoyang.event.dto.PageDto;

public interface NewsService extends BaseService<News> {
	
	PageDto list(int eventId, String searchText, PageModel pageModel);
	
	void delete(int id);
}

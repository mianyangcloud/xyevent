package com.xiaoyang.event.service;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.Event;
import com.xiaoyang.event.dto.PageDto;

public interface EventService extends BaseService<Event>{

	PageDto list(int accountId, String searchText, PageModel pageModel);
}

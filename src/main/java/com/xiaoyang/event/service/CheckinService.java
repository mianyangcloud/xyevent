package com.xiaoyang.event.service;

import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.domain.Checkin;
import com.xiaoyang.event.dto.PageDto;

public interface CheckinService extends BaseService<Checkin>{

	PageDto list(int userId, PageModel pageModel);
}

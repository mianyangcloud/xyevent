package com.xiaoyang.event.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dao.EventMapper;
import com.xiaoyang.event.domain.Event;
import com.xiaoyang.event.dto.EventDto;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.EventService;
import com.xiaoyang.event.utils.DateUtil;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventMapper eventMapper;
	
	@Override
	public PageDto list(int accountId, String searchText, PageModel pageModel) {
        Page<Event> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
        if(StringUtils.isEmpty(searchText)) {
			searchText = "";
		}else{
			searchText = "%" + searchText + "%";
		}
        List<Event> list = eventMapper.list(accountId, searchText);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<EventDto> dtoList = Lists.newArrayList();
        	for(Event event : list) {
        		EventDto eventDto = EventDto.of();
        		BeanUtils.copyProperties(event, eventDto);
        		eventDto.setBTime(DateUtil.stampToDate1(event.getBeginTime()));
        		dtoList.add(eventDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}

	@Override
	public void add(Event event) {
		int ret = eventMapper.add(event);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Event event) {
		int ret = eventMapper.update(event);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Event findById(int id) {
		return eventMapper.findById(id);
	}
}

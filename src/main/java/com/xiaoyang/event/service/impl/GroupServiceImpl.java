package com.xiaoyang.event.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dao.GroupMapper;
import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupMapper groupMapper;
	
	@Override
	public List<Group> list(int type, int eventId, String searchText) {
		if(StringUtils.isEmpty(searchText)) {
			searchText = "";
		}else{
			searchText = "%" + searchText + "%";
		}
		List<Group> list = groupMapper.list(type, eventId, searchText);
		return list;
	}
	
	@Override
	public PageDto list(int type, int eventId, String searchText, PageModel pageModel) {
        Page<Group> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
        List<Group> list = list(type, eventId, searchText);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	pageDto.setRows(list);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}

	@Override
	public void add(Group group) {
		int ret = groupMapper.add(group);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Group group) {
		int ret = groupMapper.update(group);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Group findById(int id) {
		return groupMapper.findById(id);
	}
	
	@Override
	public void delete(int groupId) {
		groupMapper.delete(groupId);
	}
}

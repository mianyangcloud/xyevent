package com.xiaoyang.event.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dao.PictureMapper;
import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.domain.Picture;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.dto.PictureDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.GroupService;
import com.xiaoyang.event.service.PictureService;
import com.xiaoyang.event.utils.DateUtil;

@Service
public class PictureServiceImpl implements PictureService{

	@Autowired
	PictureMapper pictureMapper;
	
	@Autowired
	GroupService groupService;
	
	@Override
	public PageDto listByUserId(int userId, PageModel pageModel) {
        Page<Picture> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
		List<Picture> list = pictureMapper.listByUserId(userId);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<Group> groupList = groupService.list(0, list.get(0).getEventId(), null);
        	Map groupMap = Maps.newHashMap();
        	for(Group group : groupList) {
        		groupMap.put(group.getId(), group.getTitle());
        	}
        	List<PictureDto> dtoList = Lists.newArrayList();
        	for(Picture picture : list) {
        		PictureDto pictureDto = PictureDto.of();
        		BeanUtils.copyProperties(picture, pictureDto);
        		pictureDto.setCreateTime(DateUtil.stampToDate(picture.getCtime().getTime()));
        		if(groupMap.get(pictureDto.getGroupId()) != null) {
        			pictureDto.setGroupTitle(groupMap.get(pictureDto.getGroupId()).toString());
        		}
        		dtoList.add(pictureDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
	
	@Override
	public PageDto listByGroupId(int groupId, PageModel pageModel) {
        Page<Picture> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
		List<Picture> list = pictureMapper.listByGroupId(groupId);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<Group> groupList = groupService.list(0, list.get(0).getEventId(), null);
        	Map groupMap = Maps.newHashMap();
        	for(Group group : groupList) {
        		groupMap.put(group.getId(), group.getTitle());
        	}
        	List<PictureDto> dtoList = Lists.newArrayList();
        	for(Picture picture : list) {
        		PictureDto pictureDto = PictureDto.of();
        		BeanUtils.copyProperties(picture, pictureDto);
        		pictureDto.setCreateTime(DateUtil.stampToDate(picture.getCtime().getTime()));
        		if(groupMap.get(pictureDto.getGroupId()) != null) {
        			pictureDto.setGroupTitle(groupMap.get(pictureDto.getGroupId()).toString());
        		}
        		dtoList.add(pictureDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
	
	@Override
	public PageDto list(int eventId, PageModel pageModel) {
        Page<Picture> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
		List<Picture> list = pictureMapper.list(eventId);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<Group> groupList = groupService.list(0, eventId, null);
        	Map groupMap = Maps.newHashMap();
        	for(Group group : groupList) {
        		groupMap.put(group.getId(), group.getTitle());
        	}
        	List<PictureDto> dtoList = Lists.newArrayList();
        	for(Picture picture : list) {
        		PictureDto pictureDto = PictureDto.of();
        		BeanUtils.copyProperties(picture, pictureDto);
        		pictureDto.setCreateTime(DateUtil.stampToDate(picture.getCtime().getTime()));
        		if(groupMap.get(pictureDto.getGroupId()) != null) {
        			pictureDto.setGroupTitle(groupMap.get(pictureDto.getGroupId()).toString());
        		}
        		dtoList.add(pictureDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
	
	@Override
	public void update(int groupId, String pictureId) {
		pictureId = pictureId.trim().replace(" ", "");
		updateByArrayId(groupId, pictureId.split(","));
	}
	
	@Override
	public void delete(String id) {
		id = id.trim().replace(" ", "");
		deleteByArrayId(id.split(","));
	}

	@Override
	public void add(Picture picture) {
		int ret = pictureMapper.add(picture);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Picture picture) {
		int ret = pictureMapper.update(picture);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Picture findById(int id) {
		return pictureMapper.findById(id);
	}
	
	@Override
	public void deleteByArrayId(String[] idArray) {
		pictureMapper.deleteByArrayId(idArray);
	}
	
	@Override
	public void updateByArrayId(Integer groupId, String[] idArray) {
		pictureMapper.updateByArrayId(groupId, idArray);
	}
	
	@Override
	public List<Picture> list(int eventId) {
		List<Picture> list = pictureMapper.list(eventId);
		return list;
	}
	
	@Override
	public List<Picture> listByGroupId(int groupId) {
		List<Picture> list = pictureMapper.listByGroupId(groupId);
		return list;
	}
	
	@Override
	public List<Picture> listByUserId(int userId) {
		List<Picture> list = pictureMapper.listByUserId(userId);
		return list;
	}
}

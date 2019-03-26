package com.xiaoyang.event.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dao.CheckinMapper;
import com.xiaoyang.event.domain.Checkin;
import com.xiaoyang.event.dto.CheckinDto;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.CheckinService;
import com.xiaoyang.event.utils.DateUtil;

@Service
public class CheckinServiceImpl implements CheckinService{

	@Autowired
	CheckinMapper checkinMapper;
	
	@Override
	public PageDto list(int userId, PageModel pageModel) {
        Page<Checkin> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
		List<Checkin> list = checkinMapper.list(userId);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<CheckinDto> dtoList = Lists.newArrayList();
        	for(Checkin checkin : list) {
        		CheckinDto checkinDto = CheckinDto.of();
        		BeanUtils.copyProperties(checkin, checkinDto);
        		checkinDto.setCreateTime(DateUtil.stampToDate(checkin.getCtime().getTime()));
        		dtoList.add(checkinDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}

	@Override
	public void add(Checkin carInfo) {
		int ret = checkinMapper.add(carInfo);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Checkin carInfo) {
		return;
	}

	@Override
	public Checkin findById(int id) {
		return checkinMapper.findById(id);
	}
}

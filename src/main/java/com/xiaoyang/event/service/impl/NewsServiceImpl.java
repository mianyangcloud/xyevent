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
import com.xiaoyang.event.dao.NewsMapper;
import com.xiaoyang.event.domain.News;
import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.dto.NewsDto;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.dto.UserDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.NewsService;
import com.xiaoyang.event.utils.DateUtil;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsMapper newsMapper;
	
	@Override
	public PageDto list(int eventId, String searchText, PageModel pageModel) {
		Page<News> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
		if(StringUtils.isEmpty(searchText)) {
			searchText = "";
		}else{
			searchText = "%" + searchText + "%";
		}
		List<News> list = newsMapper.list(eventId, searchText);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<NewsDto> dtoList = Lists.newArrayList();
        	for(News news : list) {
        		NewsDto newsDto = NewsDto.of();
        		BeanUtils.copyProperties(news, newsDto);
        		newsDto.setCreateTime(DateUtil.stampToDate(news.getCtime().getTime()));
        		dtoList.add(newsDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}

	@Override
	public void add(News news) {
		int ret = newsMapper.add(news);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(News news) {
		int ret = newsMapper.update(news);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public News findById(int id) {
		return newsMapper.findById(id);
	}
	
	@Override
	public void delete(int id) {
		newsMapper.delete(id);
	}
}

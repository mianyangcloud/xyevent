package com.xiaoyang.event.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jacoco.agent.rt.internal_1f1cc91.core.internal.flow.IFrame;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.dao.UserMapper;
import com.xiaoyang.event.domain.User;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.dto.UserDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.UserService;
import com.xiaoyang.event.utils.DateUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public PageDto list(int eventId, String searchText, PageModel pageModel) {
		return listByType(eventId,searchText,pageModel,0);
	}
	
	@Override
	public User findByMobilenum(int eventId, String mobilenum) {
		return userMapper.findByMobilenum(eventId, mobilenum);
	}
	
	@Override
	public User findByEmail(int eventId, String email) {
		return userMapper.findByEmail(eventId, email);
	}
	
	@Override
	public void delete(int id) {
		User user = User.of().setStatus(1).setId(id);
		update(user);
	}

	@Override
	public void add(User user) {
		int ret = userMapper.add(user);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(User user) {
		int ret = userMapper.update(user);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public User findById(int id) {
		return userMapper.findById(id);
	}
	
	@Override
	public boolean updateCode(int eventId, int code, String mobilenum) {
		int ret = userMapper.updateCode(eventId, code, mobilenum);
		if (ret == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRealName(int eventId,int userId,String realname){
		int ret = userMapper.updateRealName(eventId, userId,realname);
		if (ret == 1) {
			return true;
		}
		return false;
	}

    @Override
    public PageDto listByName(int eventId, String name, PageModel pageModel) {
	  return listByType(eventId,name,pageModel,1);
    }

//  searchType 1 按realname查找 0 默认 mapper list查找
    private PageDto listByType(int eventId, String name, PageModel pageModel,int searchType) {
		Page<User> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
		if(StringUtils.isEmpty(name)) {
			name = "";
		}else{
			name = "%" + name + "%";
		}
		List<User> list;
		if (searchType == 1) {
			list = userMapper.listByName(eventId,name);
		} else {
			list = userMapper.list(eventId,name);
		}
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
		}else {
			List<UserDto> dtoList = Lists.newArrayList();
			for(User user : list) {
				UserDto userDto = UserDto.of();
				BeanUtils.copyProperties(user, userDto);
				userDto.setCreateTime(DateUtil.stampToDate(user.getCtime().getTime()));
				if(userDto.getStatus() == 0) {
					userDto.setStatusStr("启用");
				}else {
					userDto.setStatusStr("禁用");
				}
				dtoList.add(userDto);
			}
			pageDto.setRows(dtoList);
		}
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
}

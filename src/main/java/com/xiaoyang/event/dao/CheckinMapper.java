package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.Checkin;

@Mapper
public interface CheckinMapper extends BaseMapper<Checkin>{
	
	List<Checkin> list(int userId);
}

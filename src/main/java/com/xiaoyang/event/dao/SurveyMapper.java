package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.Survey;

@Mapper
public interface SurveyMapper extends BaseMapper<Survey>{

//	int add(Survey survey);
//	
//	int update(Survey survey);
//	
//	Survey findById(int id);
	
	List<Survey> findSurveyList(int accountId);
}

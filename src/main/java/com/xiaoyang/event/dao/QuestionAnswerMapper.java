package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.QuestionAnswer;

@Mapper
public interface QuestionAnswerMapper{

	int add(QuestionAnswer questionAnswer);
	
	List<QuestionAnswer> findAnswerList(int surveyId);
}

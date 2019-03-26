package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.Question;

@Mapper
public interface QuestionMapper extends BaseMapper<Question>{

//	int add(Question question);
//	
//	int update(Question question);
//	
//	Question findById(int id);
	
	List<Question> findQuestionList(int surveyId);
}

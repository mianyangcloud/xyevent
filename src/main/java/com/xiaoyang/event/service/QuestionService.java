package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.domain.Question;

public interface QuestionService extends BaseService<Question>{
	
	List<Question> findQuestionList(int surveyId);
}

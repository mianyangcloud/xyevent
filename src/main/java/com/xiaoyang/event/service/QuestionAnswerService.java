package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.domain.QuestionAnswer;

public interface QuestionAnswerService{
	
	void add(QuestionAnswer questionAnswer);
	
	List<QuestionAnswer> findAnswerList(int surveyId);

}

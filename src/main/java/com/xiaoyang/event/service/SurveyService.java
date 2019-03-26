package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.domain.Survey;

public interface SurveyService extends BaseService<Survey>{
	
	List<Survey> findSurveyList(int accountId);
}

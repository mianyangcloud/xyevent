package com.xiaoyang.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.event.dao.SurveyMapper;
import com.xiaoyang.event.domain.Survey;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{

	@Autowired
	SurveyMapper surveyMapper;

	@Override
	public void add(Survey survey) {
		int ret = surveyMapper.add(survey);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Survey survey) {
		int ret = surveyMapper.update(survey);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Survey findById(int id) {
		return surveyMapper.findById(id);
	}

	@Override
	public List<Survey> findSurveyList(int accountId) {
		return surveyMapper.findSurveyList(accountId);
	}
}

package com.xiaoyang.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.event.dao.QuestionAnswerMapper;
import com.xiaoyang.event.domain.QuestionAnswer;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.QuestionAnswerService;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService{

	@Autowired
	QuestionAnswerMapper questionAnswerMapper;

	@Override
	public void add(QuestionAnswer questionAnswer) {
		int ret = questionAnswerMapper.add(questionAnswer);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public List<QuestionAnswer> findAnswerList(int surveyId) {
		return questionAnswerMapper.findAnswerList(surveyId);
	}
}

package com.xiaoyang.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.event.dao.QuestionMapper;
import com.xiaoyang.event.domain.Question;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	QuestionMapper questionMapper;

	@Override
	public void add(Question question) {
		int ret = questionMapper.add(question);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Question question) {
		int ret = questionMapper.update(question);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Question findById(int id) {
		return questionMapper.findById(id);
	}

	@Override
	public List<Question> findQuestionList(int surveyId) {
		return questionMapper.findQuestionList(surveyId);
	}
}

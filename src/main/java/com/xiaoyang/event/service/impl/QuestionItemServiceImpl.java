package com.xiaoyang.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.event.dao.QuestionItemMapper;
import com.xiaoyang.event.domain.QuestionItem;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.QuestionItemService;

@Service
public class QuestionItemServiceImpl implements QuestionItemService{

	@Autowired
	QuestionItemMapper questionItemMapper;

	@Override
	public void add(QuestionItem questionItem) {
		int ret = questionItemMapper.add(questionItem);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(QuestionItem questionItem) {
		int ret = questionItemMapper.update(questionItem);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public QuestionItem findById(int id) {
		return questionItemMapper.findById(id);
	}

	@Override
	public List<QuestionItem> findItemList(int questionId) {
		return questionItemMapper.findItemList(questionId);
	}
}

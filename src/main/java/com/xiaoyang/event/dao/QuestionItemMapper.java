package com.xiaoyang.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoyang.event.domain.QuestionItem;

@Mapper
public interface QuestionItemMapper extends BaseMapper<QuestionItem>{

//	int add(QuestionItem questionItem);
//	
//	int update(QuestionItem questionItem);
//	
//	QuestionItem findById(int id);
	
	List<QuestionItem> findItemList(int questionId);
}

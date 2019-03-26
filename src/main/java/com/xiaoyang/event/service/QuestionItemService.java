package com.xiaoyang.event.service;

import java.util.List;

import com.xiaoyang.event.domain.QuestionItem;

public interface QuestionItemService extends BaseService<QuestionItem>{
	
	List<QuestionItem> findItemList(int questionId);

}

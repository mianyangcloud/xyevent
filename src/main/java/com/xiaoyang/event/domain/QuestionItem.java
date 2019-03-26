package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionItem {

	private Integer id;
	
	private String title;
	
	private int index;
	
	private int questionId;
	
	private Date ctime;
    
    private Date utime;
}

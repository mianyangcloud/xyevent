package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Question {

	private Integer id;
	
	private String title;
	
	private int index;
	
	private int type;
	
	private int surveyId;
	
	private Date ctime;
    
    private Date utime;
}

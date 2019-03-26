package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionAnswer {

	private Integer id;
	
	private int questionId;
	
	private int userId;
	
	private String content;
	
	private Date ctime;
    
    private Date utime;
}

package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Event {

	private Integer id;
	
	private String title;
	
	private String description;
	
	private Integer accountId;
	
	private Long beginTime;
	
	private Long endTime;
	
	private Date ctime;
    
    private Date utime;
}

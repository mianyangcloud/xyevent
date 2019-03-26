package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Survey {

	private Integer id;
	
	private String title;
	
	private int accountId;
	
	private Date ctime;
    
    private Date utime;
}

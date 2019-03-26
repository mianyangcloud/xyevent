package com.xiaoyang.event.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageDto {

	private List rows;
	
	private Integer total;
}

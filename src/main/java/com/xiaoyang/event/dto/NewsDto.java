package com.xiaoyang.event.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class NewsDto {

	private Integer id;
	
	private Integer eventId;
	
	private String title;
	
	private String imageUrl;
	
	private String introduce;
	
	private String httpUrl;
	
	private String content;
	
	private Integer level;
	
	private String createTime;
}

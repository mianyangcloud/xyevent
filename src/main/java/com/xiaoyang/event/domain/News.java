package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class News {

	private Integer id;
	
	private Integer eventId;
	
	private String title;
	
	private String imageUrl;
	
	private String introduce;
	
	private String httpUrl;
	
	private String content;
	
	private Integer level;
	
	private Date ctime;
    
    private Date utime;
}

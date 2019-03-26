package com.xiaoyang.event.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class CheckinDto {

	private Integer id;
	
	private Integer userId;
	
	private Integer eventId;
	
	private String address;
	
	private String createTime;
	
	private Date ctime;
    
    private Date utime;
}

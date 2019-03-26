package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class Checkin {

	private Integer id;
	
	private Integer userId;
	
	private Integer eventId;
	
	private String address;
	
	private Date ctime;
    
    private Date utime;
}

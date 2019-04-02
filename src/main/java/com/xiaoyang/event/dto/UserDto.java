package com.xiaoyang.event.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class UserDto {

	private Integer id;
	
	private Integer eventId;
	
	private String openId;
	
	private String realname;
	
	private String mobilenum;
	
	private String email;
	
	private String company;
	
	private String remark;
	
	private Integer status;
	
	private Integer privacy;
	
	private Integer pictureNum;
	
	private Integer videoNum;
	
	private String statusStr;
	
	private String createTime;
}

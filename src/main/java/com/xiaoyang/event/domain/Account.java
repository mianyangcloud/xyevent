package com.xiaoyang.event.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class Account {

	private Integer id;
	
	@NotNull
	private String realname;
	
	@NotNull
	private String mobilenum;
	
	@NotNull
	private String password;
	
	@NotNull
	private String company;
	
	private Integer status;
	
	private Date ctime;
    
    private Date utime;
}

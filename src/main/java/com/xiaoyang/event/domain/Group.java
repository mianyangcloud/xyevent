package com.xiaoyang.event.domain;

import java.util.Date;

import lombok.Data;

/**
 * 分组
 * @author magic
 */
@Data
public class Group {

	private Integer id;
	
	private String title;//组别名称
	
	private Integer eventId;//活动Id
	
	private Integer level;//组别排序等级
	
	private Integer type;//组别分类 0 图片直播
	
	private Date ctime;
    
    private Date utime;
}

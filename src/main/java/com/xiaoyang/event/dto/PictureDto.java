package com.xiaoyang.event.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class PictureDto {

	private Integer id;
	
	private String title;//图片标题
	
	private String url;//图片地址
	
	private Integer eventId;//活动ID
	
	private Integer groupId;//分组名称
	
	private String groupTitle;
	
	private Long size;//图片大小
	
	private Integer status;//0 未删除 1 删除
	
	private String createTime;
	
	private Date ctime;
    
    private Date utime;
}

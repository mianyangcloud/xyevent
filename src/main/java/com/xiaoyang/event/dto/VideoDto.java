package com.xiaoyang.event.dto;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Data
public class VideoDto {

	private Integer id;
	
	private Integer eventId;
	
	private String title;//视频标题
	
	private String fileName;//文件名称
	
	private String headImage;//视频头图
	
	private String formats;//视频格式
	
	private long size;//视频容量
	
	private String time;//视频时长
	
	/**
	 * 0:上传中, 1:上传失败, 2:上传完成, 3:转码中, 4:转码失败, 5:审核中, 6:屏蔽, 7:正常
	 */
	private Integer status = 0 ;//视频状态
	
	private String odAddress;//视频原始地址
	
	private String ldAddress;//视频标清地址
	
	private Integer groupId;//分组名称
	
	private String groupTitle;
	
	private String videoId;//阿里云字段
	
	private String createTime;
	
	private Date ctime;
    
    private Date utime;
}

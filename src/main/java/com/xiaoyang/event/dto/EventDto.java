package com.xiaoyang.event.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.xiaoyang.event.domain.Event;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@Slf4j
@Data
public class EventDto {

	private Integer id;
	
	private String title;
	
	private String description;
	
	private Integer accountId;
	
	private String bTime;
	
	private String eTime;
	
	/**
	 * 转换为Event
	 */
	public Event convertToEvent() {
		Event event = new Event();
		BeanUtils.copyProperties(this, event);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = format.parse(this.getBTime());
			event.setBeginTime(date.getTime());
		} catch (ParseException e) {
			log.error("日期转换出错", e);
		}
		return event;
	}
}

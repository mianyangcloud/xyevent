package com.xiaoyang.event.constant;

import com.xiaoyang.event.common.ResultCode;

import lombok.Getter;

/**
 * 返回结果错误码，系统级别错误100-999，
 * 系统级别错误100-999，按系统级别错误划分公共级别错误开头数字。
 *
 * @date 2018-12-12
 */
@Getter
public enum EventCodes implements ResultCode{
	
	/**
	 * 0-99 账号错误
	 */
	 ACCOUNT_NOT_EXIST_ERROR(10000, "账号不存在，或账号密码错误");
	
	/**
	 * 代码值
	 */
	private int code;
	
	/**
	 * 代码值描述
	 */
	private String desc;
	
	private EventCodes(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}

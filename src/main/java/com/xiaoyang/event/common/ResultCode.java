package com.xiaoyang.event.common;

public interface ResultCode {

	/**
	 * 获取返回码
	 * @return int
	 */
	public int getCode();
	
	/**
	 * 获取返回码的值
	 * @return String
	 */
	public String getDesc();
}

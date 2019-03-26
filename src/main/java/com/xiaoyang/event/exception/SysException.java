package com.xiaoyang.event.exception;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyang.event.common.ResultCode;
import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.utils.RequestIDUtil;

import lombok.Getter;

@Getter
public class SysException extends RuntimeException{

private int errorCode;
	
	private String errorMessage;
	
	private ResultCode resultCode;
	
	public SysException() {
		this(CommonCodes.ERROR_UNKNOWN);
	}
	
	public SysException(ResultCode resultCode) {
		this(resultCode, "");
	}
	
	public SysException(ResultCode resultCode, String msg) {
		super(format(resultCode));
		init(resultCode, msg);
	}
	
	public SysException(ResultCode resultCode, Throwable e) {
		this(resultCode, "", e);
	}
	
	public SysException(ResultCode resultCode, String msg, Throwable e) {
		super(format(resultCode), e);
		init(resultCode, msg);
	}
	
	public String toJsonString() {
		JSONObject jo = new JSONObject();
		jo.put("errorCode", errorCode);
		jo.put("errorMsg", errorMessage);
		jo.put("stime", System.currentTimeMillis());
		jo.put("requestId", RequestIDUtil.getRequestId());
		return jo.toJSONString();
	}
	
	private void init(ResultCode resultCode, String msg) {
		this.errorCode = resultCode.getCode();
		if(StringUtils.isEmpty(msg)) {
			this.errorMessage = this.getMessage();
		} else {
			this.errorMessage = this.getMessage() + ", " + msg;
		}
		this.resultCode = resultCode;
	}
	
	private static String format(ResultCode resultCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("errorCode:")
		.append(resultCode.getCode())
		.append(", ")
		.append("errorMsg:")
		.append(resultCode.getDesc());
		return sb.toString();
	}
}

package com.xiaoyang.event.exception;

import com.xiaoyang.event.common.ResultCode;
import com.xiaoyang.event.constant.CommonCodes;

public class ParamsException extends SysException {

	public ParamsException() {
		super(CommonCodes.ERROR_PARAMS);
	}
	
	public ParamsException(String msg) {
		super(CommonCodes.ERROR_PARAMS, msg);
	}
	
	public ParamsException(ResultCode resultCode) {
		super(resultCode);
	}
	
	public ParamsException(ResultCode resultCode, String msg) {
		super(resultCode, msg);
	}
}

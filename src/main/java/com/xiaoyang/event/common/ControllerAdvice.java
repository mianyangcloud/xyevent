package com.xiaoyang.event.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.exception.ParamsException;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.utils.LogUtil;

@RestControllerAdvice
public class ControllerAdvice {
	
	/**
	 * 处理参数异常
	 * @param e 参数异常
	 * @return ResultResp
	 */
	@ExceptionHandler(ParamsException.class)
	protected ResultResp handleParamsException(ParamsException e) {
		LogUtil.logParamsException(e);
		return ResultResp.returnError(CommonCodes.ERROR_PARAMS, e.getErrorMessage());
	}
	
	/**
	 * 处理全局异常
	 * @param e 全局异常
	 * @return ResultResp
	 */
	@ExceptionHandler(Exception.class)
	protected ResultResp handleException(Exception e) {
		LogUtil.logException(e);
		if (e instanceof SysException) {
			return ResultResp.returnError(((SysException) e), ((SysException) e).getErrorMessage());
		}else {
			return ResultResp.returnError(CommonCodes.ERROR_UNKNOWN);
		}
	}
}

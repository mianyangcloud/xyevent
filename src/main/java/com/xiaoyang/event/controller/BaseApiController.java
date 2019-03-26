package com.xiaoyang.event.controller;

import com.xiaoyang.event.common.ResultCode;
import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.exception.SysException;

public class BaseApiController {

	/**
     * 返回成功
     *
     * @return ResultResp
     */
    public ResultResp returnSuccess() {
        return new ResultResp();
    }
    
    /**
     * 返回成功
     *
     * @param data 附加数据
     * @return ResultResp
     */
    public ResultResp returnSuccess(Object data) {
        return new ResultResp(CommonCodes.SUCCESS, data);
    }
    
    /**
     * 返回成功
     *
     * @param data 附加数据
     * @param message 描述信息
     * @return ResultResp
     */
    public ResultResp returnSuccess(Object data, String message) {
    	return new ResultResp(CommonCodes.SUCCESS, message, data);
    }

    /**
     * 返回错误
     *
     * @param resultCode 错误码
     * @return ResultResp
     */
    public ResultResp returnError(ResultCode resultCode) {
    	return new ResultResp(resultCode, null);
    }

    /**
     * 返回错误
     *
     * @param resultCode 错误码
     * @param message 描述信息
     * @return ResultResp
     */
    public ResultResp returnError(ResultCode resultCode, String message) {
    	return new ResultResp(resultCode, message, null);
    }
    
    /**
     * 返回错误
     *
     * @param SysException 异常
     * @param message 描述信息
     * @return ResultResp
     */
    public ResultResp returnError(SysException e) {
    	return new ResultResp(e.getResultCode(), null);
    }
    
    /**
     * 返回错误
     *
     * @param SysException 异常
     * @param message 描述信息
     * @return ResultResp
     */
    public ResultResp returnError(SysException e, String message) {
    	return new ResultResp(e.getResultCode(), message, null);
    }
}

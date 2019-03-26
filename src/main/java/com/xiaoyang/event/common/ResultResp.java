package com.xiaoyang.event.common;

import com.xiaoyang.event.constant.CommonCodes;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.utils.RequestIDUtil;

import lombok.Getter;

@Getter
public class ResultResp {
	
    /**
     * 返回代码
     */
    private Integer code;
    
    /**
     * 返回信息
     */
    private String msg;
    
    /**
     * 返回数据
     */
    private Object data;
    
    /**
     * 请求时间
     */
    private Long stime = System.currentTimeMillis();
    
    /**
     * 请求ID
     */
    private Integer requestId = RequestIDUtil.getRequestId();
    
    public ResultResp() {
        this.code = CommonCodes.SUCCESS.getCode();
        this.msg = CommonCodes.SUCCESS.getDesc();
    }
    
    public ResultResp(ResultCode resultCode, Object data) {
    	this.stime = System.currentTimeMillis();
        this.requestId = RequestIDUtil.getRequestId();
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
        this.data = data;
    }
    
    public ResultResp(ResultCode resultCode, String msg, Object data) {
    	this.stime = System.currentTimeMillis();
        this.requestId = RequestIDUtil.getRequestId();
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
    }
    
    /**
     * 返回成功
     *
     * @return ResultResp
     */
    public static ResultResp returnSuccess() {
        return new ResultResp();
    }
    
    /**
     * 返回成功
     *
     * @param data 附加数据
     * @return ResultResp
     */
    public static ResultResp returnSuccess(Object data) {
        return new ResultResp(CommonCodes.SUCCESS, data);
    }
    
    /**
     * 返回成功
     *
     * @param data 附加数据
     * @param message 描述信息
     * @return ResultResp
     */
    public static ResultResp returnSuccess(Object data, String message) {
    	return new ResultResp(CommonCodes.SUCCESS, message, data);
    }

    /**
     * 返回错误
     *
     * @param resultCode 错误码
     * @return ResultResp
     */
    public static ResultResp returnError(ResultCode resultCode) {
    	return new ResultResp(resultCode, null);
    }

    /**
     * 返回错误
     *
     * @param resultCode 错误码
     * @param message 描述信息
     * @return ResultResp
     */
    public static ResultResp returnError(ResultCode resultCode, String message) {
    	return new ResultResp(resultCode, message, null);
    }
    
    /**
     * 返回错误
     *
     * @param SysException 异常
     * @param message 描述信息
     * @return ResultResp
     */
    public static ResultResp returnError(SysException e) {
    	return new ResultResp(e.getResultCode(), null);
    }
    
    /**
     * 返回错误
     *
     * @param SysException 异常
     * @param message 描述信息
     * @return ResultResp
     */
    public static ResultResp returnError(SysException e, String message) {
    	return new ResultResp(e.getResultCode(), message, null);
    }

    @Override
    public String toString() {
        return "ResultResp{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", stime=" + stime +
                ", requestId=" + requestId +
                '}';
    }
}
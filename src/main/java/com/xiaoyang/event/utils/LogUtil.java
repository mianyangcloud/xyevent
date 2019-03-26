package com.xiaoyang.event.utils;

import com.xiaoyang.event.exception.SysException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {

	/**
	 * 异常日志记录
	 * @param e 异常信息
	 */
	public static void logException(Throwable e) {
		log.error(generalMsg(e), e);
	}
	
	/**
	 * 参数异常日志记录
	 * @param e 异常信息
	 */
	public static void logParamsException(Throwable e) {
		log.error(generalMsg(e));
	}
	
	/**
	 * 信息日志记录
	 * @param msg 日志信息
	 */
	public static void logInfo(String msg) {
        log.info(generalMsg(msg));
	}
	
	/**
	 * 错误日志记录
	 * @param msg 日志信息
	 */
	public static void logError(String msg) {
        log.error(generalMsg(msg));
	}
	
	/**
	 * 警告日志记录
	 * @param msg 日志信息
	 */
	public static void logWarn(String msg) {
        log.warn(generalMsg(msg));
	}
	
	/**
	 * http请求信息日志记录
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param results 返回信息
	 */
	public static void logHttp(String url, String params, String results) {
		StringBuilder sb = new StringBuilder();
		sb.append("url:").append(url).append(", ")
			.append("params:").append(params).append(", ")
			.append("results").append(results);
		log.info(generalMsg(sb.toString()));
	}
	
	/**
	 * 日志常规信息
	 * @param msg 日志信息
	 * @return String 常规信息
	 */
	public static String generalMsg(String msg) {
		String requestId = "0";
		if(RequestIDUtil.getRequestId() != null) {
			requestId = RequestIDUtil.getRequestId().toString();
		}
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		return brackets(trace[3].getClassName())
				+ brackets(trace[3].getMethodName())
				+ brackets(String.valueOf(trace[3].getLineNumber()))
				+ brackets("requestId:" + requestId)
				+ msg;
	}
	
	/**
	 * 异常日志常规信息
	 * @param e 异常信息
	 * @return String 常规信息
	 */
	public static String generalMsg(Throwable e) {
		String requestId = "0";
		if(RequestIDUtil.getRequestId() != null) {
			requestId = RequestIDUtil.getRequestId().toString();
		}
		String msg = "";
		if(e instanceof SysException) {
			msg = ((SysException)e).getErrorMessage();
		}
		StackTraceElement[] trace = e.getStackTrace();
		return brackets(trace[0].getClassName())
				+ brackets(trace[0].getMethodName())
				+ brackets(String.valueOf(trace[0].getLineNumber()))
				+ brackets("requestId:" + requestId)
				+ msg;
	}
	
	/**
	 * 日志括号样式
	 * @param str 字符串
	 * @return String 括号样式字符串
	 */
	private static String brackets(String str) {
		return "[" + str + "]";
	}
}

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
public enum CommonCodes implements ResultCode{
	
	/**
	 * 成功
	 */
	 SUCCESS(1, "成功"),
	
	/**
	 * 系统级别错误100-999
	 */
	 ERROR_SYSTEM(100, "系统错误"),
	
	 ERROR_UNKNOWN(200, "未知错误"),
	
	 ERROR_NETWORK_CONNECT(300, "网络连接失败"),
	
	 ERROR_DB_CONNECT(400, "数据库连接失败"),
	 
	 ERROR_IO(500, "IO异常错误"),
	
	/**
	 * 公共级别错误1000-9999
	 */
	 ERROR_PARAMS(1000, "参数错误"),
	 
	 ERROR_ENCODING(1001, "编码错误，不支持的编码"),
	
	/**
	 * 数据库操作错误
	 */
	 ERROR_DB_INSERT(4000, "数据库添加失败"),
	
	 ERROR_DB_UPDATE(4001, "数据库更新失败"),
	
	 ERROR_DB_DELETE(4002, "数据库删除失败");
	
	/**
	 * 代码值
	 */
	private int code;
	
	/**
	 * 代码值描述
	 */
	private String desc;
	
	private CommonCodes(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}

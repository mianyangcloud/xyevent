package com.xiaoyang.event.common;

public class PageModel {
	/**
	 * 页面显示数量
	 */
	private Integer pageSize;
	/**
	 * 当前页数
	 */
	private Integer pageNumber;
	/**
	 * 开始条数(LIMIT第一个参数)
	 */
	private Integer start;

	private final int MAX_SIZE = 50000;
	
	private final int MIN_SIZE = 1;
	
	private final int DEFAULT_SIZE = 20;

	private final int MIN_NUM = 1;
	
	public Integer getPageSize() {
		if (pageSize == null) {
			return DEFAULT_SIZE;
		} else if (pageSize < MIN_SIZE) {
			return MIN_SIZE;
		} else if (pageSize > MAX_SIZE) {
			return MAX_SIZE;
		}
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageNumber() {
		if (pageNumber == null || pageNumber < MIN_NUM) {
			return MIN_NUM;
		}
		return pageNumber;
	}
	
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getStart() {
	    if (start != null) {
	        return start;
	    }
		return (getPageNumber() - 1) * getPageSize();
	}
	
	public void setStart(int start) {
	    this.start = start;
	}
	
	public int getCount() {
		return getPageSize();
	}
}
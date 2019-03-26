package com.xiaoyang.event.dao;

public interface BaseMapper<T> {

	int add(T t);
	
	int update(T t);
	
	T findById(int id);
}

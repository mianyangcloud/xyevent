package com.xiaoyang.event.service;

public interface BaseService<T> {
	
	void add(T t);
	
	void update(T t);
	
	T findById(int id);
}

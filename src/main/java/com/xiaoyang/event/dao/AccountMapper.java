package com.xiaoyang.event.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xiaoyang.event.domain.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account>{

//	int add(Account account);
//	
//	int update(Account account);
//	
//	Account findById(int id);
	
	Account findByAccount(@Param("mobilenum")String mobilenum, @Param("password")String password);
}

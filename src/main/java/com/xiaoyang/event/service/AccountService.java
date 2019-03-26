package com.xiaoyang.event.service;

import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.domain.Account;

public interface AccountService extends BaseService<Account>{
	
	ResultResp login(String mobilenum, String password);
	
	ResultResp addAccount(Account account);
	
	ResultResp findAccount(int accountId);
	
	ResultResp updateAccount(Account account);
	
	ResultResp changeStatus(int accountId, int status);

	Account findByAccount(String mobilenum, String password);
}

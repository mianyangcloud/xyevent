package com.xiaoyang.event.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.constant.EventCodes;
import com.xiaoyang.event.dao.AccountMapper;
import com.xiaoyang.event.domain.Account;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.AccountService;
import com.xiaoyang.event.utils.HashUtil;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountMapper accountMapper;
	
	public ResultResp login(String mobilenum, String password) {
		Account account = findByAccount(mobilenum, HashUtil.md5(password));
		if(account != null) {
			return ResultResp.returnSuccess(account);
		}else {
			return ResultResp.returnError(EventCodes.ACCOUNT_NOT_EXIST_ERROR);
		}
	}
	
	public ResultResp addAccount(Account account) {
		add(account);
		return ResultResp.returnSuccess();
	}
	
	public ResultResp findAccount(int accountId) {
		Account account = findById(accountId);
		return ResultResp.returnSuccess(account);
	}
	
	public ResultResp updateAccount(Account account) {
		update(account);
		return ResultResp.returnSuccess();
	}
	
	public ResultResp changeStatus(int accountId, int status) {
		Account account = Account.of().setId(accountId).setStatus(status);
		update(account);
		return ResultResp.returnSuccess();
	}

	@Override
	public void add(Account account) {
		int ret = accountMapper.add(account);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Account account) {
		int ret = accountMapper.update(account);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Account findById(int id) {
		return accountMapper.findById(id);
	}

	@Override
	public Account findByAccount(String mobilenum, String password) {
		return accountMapper.findByAccount(mobilenum, password);
	}
}

package com.xiaoyang.event.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyang.event.common.ResultResp;
import com.xiaoyang.event.common.Validators;
import com.xiaoyang.event.domain.Account;
import com.xiaoyang.event.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountApiController {

	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/login")
	public ResultResp login(String mobilenum, String password, HttpSession httpSession) {
		ResultResp resultResp = accountService.login(mobilenum, password);
		httpSession.setAttribute("account", resultResp.getData());
		return resultResp;
	}
	
	@PostMapping(value = "/addAccount")
	public ResultResp addAccount(Account account) {
		//验证参数问题
		Validators.validate(account);
		return accountService.addAccount(account);
	}
	
	@GetMapping(value = "/findAccount")
	public ResultResp findAccount(int accountId) {
		return accountService.findAccount(accountId);
	}
	
	@PostMapping(value = "/updateAccount")
	public ResultResp updateAccount(Account account) {
		return accountService.updateAccount(account);
	}
	
	@GetMapping(value = "/changeStatus")
	public ResultResp changeStatus(int accounId, int status) {
		return accountService.changeStatus(accounId, status);
	}
}

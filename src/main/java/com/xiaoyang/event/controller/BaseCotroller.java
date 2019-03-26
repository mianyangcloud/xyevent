package com.xiaoyang.event.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoyang.event.domain.Account;
import com.xiaoyang.event.domain.Event;

public class BaseCotroller {
	
	@Autowired
	protected HttpServletRequest request;
	
	protected Account getAccount() {
		Account account = (Account) request.getSession().getAttribute("account");
		return account;
	}
	
	protected Event getEvent() {
		Event event = (Event) request.getSession().getAttribute("event");
		return event;
	}
}

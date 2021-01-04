package com.cg.apps.tatasky.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.apps.tatasky.entities.Account;
import com.cg.apps.tatasky.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/save")
	public Account saveAccount(@RequestBody Account account)
{
	
	
	Account accObj = accountService.add(account);
	return accObj;
}



}

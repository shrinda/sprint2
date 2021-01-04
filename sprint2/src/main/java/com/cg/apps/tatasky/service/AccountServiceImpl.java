package com.cg.apps.tatasky.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import com.cg.apps.tatasky.entities.Account;
import com.cg.apps.tatasky.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	
	
	@Autowired
	private AccountRepository  accountRepository;
	
	@Transactional
	@Override
	public Account add(Account account) {
		// TODO Auto-generated method stub
		Account accObj = null;
		accObj = accountRepository.save(account);
		return accObj;
	}

	@Override
	public Account findById(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		
	}

}

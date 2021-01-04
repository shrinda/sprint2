package com.cg.apps.tatasky.service;



import com.cg.apps.tatasky.entities.Account;


public interface AccountService {

	 Account add(Account account);

	    Account findById(Long accountId) ;

	    Account update(Account account);

	    void deleteByAccountId(Long accountId);

	    
}

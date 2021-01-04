package com.cg.apps.tatasky.service;



import com.cg.apps.tatasky.entities.User;

import com.cg.apps.tatasky.payload.LoginReqPayLoad;


public interface UserService {
	// By default all the methods present in interface are public abstract
	 User register(User user);
	String signIn(LoginReqPayLoad loginduser);
	User update(long id,User user);
	 User findById(Long id);
	
	User deleteByUserId(Long userid);
	
	
	String resetPassword(LoginReqPayLoad loginuser, String newpass);

	
	
	
	
	
}

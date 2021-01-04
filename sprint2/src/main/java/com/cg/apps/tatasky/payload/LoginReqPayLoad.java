package com.cg.apps.tatasky.payload;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Positive;

public class LoginReqPayLoad {

	@Positive(message ="user id must be positive and not null")
	private long userId;
	@NotEmpty(message = "Password  must be filled ")
	
	private String password;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

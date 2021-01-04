package com.cg.apps.tatasky.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="account_tbl")
public class Account  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	@Positive(message ="account id must not be null & 0")
	private long accountId;
	
	@JsonManagedReference
	@OneToOne(targetEntity = User.class,mappedBy="account")
	private User user;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", user=" + user + "]";
	}

		
	
	
	
	
}




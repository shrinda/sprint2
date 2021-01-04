package com.cg.apps.tatasky.entities;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
//This annotation is used to create and declare the table name
@Entity

//This annotation is used to create and declare the table name
@Table(name="user_tbl")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id //This annotation is used  for primary key
	
	@Column(name="id") // This  annotation is used for column name
	@Positive(message ="User Id must not be Negative & Empty ") //This annotation is used for Condition
	private long id;
	
	
	@Column(name="user_name",unique=true)
	@NotEmpty(message = "UserName  must not be empty")
	@Size(min =3, max =20, message = "UserName : Minimum  3 and  maximum 20 characters are allowed")
	private String username;
	
	
	@Column(name="first_name")
	@NotEmpty(message = "FirstName  must not be empty") //This annotations is used  for  field can not be empty
	@Size(min =3, max =20, message = " FirstName : Minimum  3 and  maximum 20 characters are allowed") // This annotation is used  for length
	private String firstName;
	
	
	@Column(name="last_name")
	@NotEmpty(message = "LastName  must not be empty")
	@Size(min =3, max =20, message = " Lastname : Minimum  3 and  maximum 20 characters are allowed")
	private String lastName;
	

	@Column(name="password")
	@NotEmpty(message = " Password  must not be empty")
   @Size(min =6, max = 20, message ="Password: Minimum 6 ana Maximum 20 charaters are allowed")
	private String password;
	
	
	@Column(name="roll")
	@NotEmpty(message = "Role  must not be empty")
	@Size(min =3, max =20, message = " Role : Minimum  3 and  maximum 20 characters are allowed")
	private String roll;
	
	@JsonBackReference
	@OneToOne(targetEntity = Account.class,cascade=CascadeType.ALL)
	@JoinColumn(name="account_id", nullable = false)
	private Account account;
	

	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	
	
	public User()
	{
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", roll=" + roll + ", account=" + account + "]";
	}
	public User(long id, String username, String firstName, String lastName, String password, String roll,
			Account account) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.roll = roll;
		this.account = account;
	}
	
	
	
	
	
	
	
}

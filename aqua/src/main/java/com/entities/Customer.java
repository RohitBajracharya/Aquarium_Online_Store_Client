package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_details")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	@Column(name="username",length=150)
	private String username;
	@Column(name="email",length = 150)
	private String email;
	@Column(name="phone_number",length = 10)
	private String phoneNo;
	@Column(name="password",length=150)
	private String password;
	public Customer(int customerId, String username, String email, String phoneNo, String password) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String username, String email, String phoneNo, String password) {
		super();
		this.username = username;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
	}
	
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setUserId(int customerId) {
		this.customerId = customerId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

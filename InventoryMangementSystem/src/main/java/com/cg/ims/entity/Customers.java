package com.cg.ims.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customers {

	
	@Id
	private int customerId;
	private String emailAddress;
	private String fullName;
	
	public Customers() {
		// TODO Auto-generated constructor stub
	}

	public Customers(int customerId, String emailAddress, String fullName) {
		super();
		this.customerId = customerId;
		this.emailAddress = emailAddress;
		this.fullName = fullName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}

package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customers {

	@Id
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "full_name")
	private String fullName;

	public Customers() {
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

	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", emailAddress=" + emailAddress + ", fullName=" + fullName
				+ "]";
	}

}

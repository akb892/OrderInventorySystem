package com.cg.ims.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Orders> order;
	


	public Customers() {
	}

	

	



	public Customers(int customerId, String emailAddress, String fullName, List<Orders> order) {
		super();
		this.customerId = customerId;
		this.emailAddress = emailAddress;
		this.fullName = fullName;
		this.order = order;
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







	public List<Orders> getOrder() {
		return order;
	}







	public void setOrder(List<Orders> order) {
		this.order = order;
	}



	

	


}

package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customers {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "email_address")
	private String emailAddress;
	
	@Column(name = "full_name")
	private String fullName;
	
	public Customers() {
		// TODO Auto-generated constructor stub
	}

	public Customers(String emailAddress, String fullName) {
		super();
		this.emailAddress = emailAddress;
		this.fullName = fullName;
	}


	
	
}

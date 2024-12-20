package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customers {

	
	@Id
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "email_address")
	private String emailAddress;
	
	@Column(name = "full_name")
	private String fullName;
	
	


	
	
}

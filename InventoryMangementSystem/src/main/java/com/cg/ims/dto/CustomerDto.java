package com.cg.ims.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDto {
	
	@NotNull(message = "Customer id cannot be null")
	@NotBlank(message = "Customer id cannot be blank")
	private int customerId;
	@Email(message = "Invalid Email")
	@Size(max = 255, message = "Email address must not exceed 255 characters")
	private String emailAddress;
	@NotNull(message = "Name should not be null")
	@NotBlank(message = "Name can not be blank")
	@Size(max = 255, message = "Full name must not exceed 255 characters")
	private String fullName;

	public CustomerDto() {
	}

	public CustomerDto(int customerId, String emailAddress, String fullName) {
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

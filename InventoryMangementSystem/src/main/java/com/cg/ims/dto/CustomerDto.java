package com.cg.ims.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for Customer entity.
 * This class is used to transfer customer data between different layers of the application.
 */
public class CustomerDto {
    
    // Unique identifier for the customer
    private int customerId;
    
    /**
     * Email address of the customer.
     * - Must be a valid email format.
     * - Maximum allowed length is 255 characters.
     */
    @Email(message = "Invalid Email")
    @Size(max = 255, message = "Email address must not exceed 255 characters")
    private String emailAddress;
    
    /**
     * Full name of the customer.
     * - Cannot be null.
     * - Cannot be blank.
     * - Maximum allowed length is 255 characters.
     */
    @NotNull(message = "Name should not be null")
    @NotBlank(message = "Name can not be blank")
    @Size(max = 255, message = "Full name must not exceed 255 characters")
    private String fullName;

    // Default constructor
    public CustomerDto() {
    }

    /**
     * Parameterized constructor to initialize CustomerDto.
     * 
     * @param customerId   Unique identifier for the customer.
     * @param emailAddress Email address of the customer.
     * @param fullName     Full name of the customer.
     */
    public CustomerDto(int customerId, String emailAddress, String fullName) {
        super();
        this.customerId = customerId;
        this.emailAddress = emailAddress;
        this.fullName = fullName;
    }

    /**
     * Getter for customer ID.
     * 
     * @return The unique identifier of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customer ID.
     * 
     * @param customerId The unique identifier to set for the customer.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for email address.
     * 
     * @return The email address of the customer.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for email address.
     * 
     * @param emailAddress The email address to set for the customer.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for full name.
     * 
     * @return The full name of the customer.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for full name.
     * 
     * @param fullName The full name to set for the customer.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

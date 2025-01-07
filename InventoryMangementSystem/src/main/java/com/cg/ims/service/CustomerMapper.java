package com.cg.ims.service;


import com.cg.ims.dto.CustomerDto;
import com.cg.ims.entity.Customers;

public class CustomerMapper {

    // Method to convert a Customers entity object to a CustomerDto object
    public static CustomerDto toDTO(Customers customer) {
        // Create a new CustomerDto object to store the data
        CustomerDto dto = new CustomerDto();
        
        // Set the properties of the CustomerDto from the Customers entity
        dto.setCustomerId(customer.getCustomerId());
        dto.setFullName(customer.getFullName());
        dto.setEmailAddress(customer.getEmailAddress());
        
        // Return the populated DTO object
        return dto;
    }

    // Method to convert a CustomerDto object to a Customers entity object
    public static Customers toEntity(CustomerDto dto) {
        // Create a new Customers entity object to store the data
        Customers customer = new Customers();
        
        // Set the properties of the Customers entity from the CustomerDto
        customer.setCustomerId(dto.getCustomerId());
        customer.setFullName(dto.getFullName());
        customer.setEmailAddress(dto.getEmailAddress());
        
        // Return the populated entity object
        return customer;
    }
}


package com.cg.ims.service;


import com.cg.ims.dto.CustomerDto;
import com.cg.ims.entity.Customers;
 
public class CustomerMapper {
 
    public static CustomerDto toDTO(Customers customer) {
        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFullName(customer.getFullName());
        dto.setEmailAddress(customer.getEmailAddress());
        return dto;
    }
 
    public static Customers toEntity(CustomerDto dto) {
        Customers customer = new Customers();
        customer.setCustomerId(dto.getCustomerId());
        customer.setFullName(dto.getFullName());
        customer.setEmailAddress(dto.getEmailAddress());
        return customer;
    }
}

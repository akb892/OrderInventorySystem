package com.cg.ims.service;

import java.util.List;

import com.cg.ims.dto.CustomersDto;
import com.cg.ims.exception.CustomerNotFoundException;

public interface ICustomerService {
	List<CustomersDto> getAllCustomers();

	CustomersDto getCustomerById(int customerid) throws CustomerNotFoundException;

	CustomersDto addCustomer(CustomersDto customerdto);

	void deleteCustomer(int customerId) throws CustomerNotFoundException;
}

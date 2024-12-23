package com.cg.ims.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ims.dao.ICustomerRepo;
import com.cg.ims.dto.CustomersDto;
import com.cg.ims.entity.Customers;
import com.cg.ims.exception.CustomerNotFoundException;
import com.cg.ims.service.interfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepo repo;

	@Override
	public List<CustomersDto> getAllCustomers() {
		List<Customers> customers = repo.findAll();
		return customers.stream().map(customer -> new CustomersDto(customer.getCustomerId(), customer.getFullName(),
				customer.getEmailAddress())).collect(Collectors.toList());
	}

	@Override
	public CustomersDto getCustomerById(int customerid) throws CustomerNotFoundException {
		Customers customer = repo.findById(customerid).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerid));
		return new CustomersDto(customer.getCustomerId(), customer.getFullName(), customer.getEmailAddress());
	}

	@Override
	public CustomersDto addCustomer(CustomersDto customerdto) {
		Customers customer = new Customers();
		customer.setCustomerId(customerdto.getCustomerId());
		customer.setFullName(customerdto.getFullName());
		customer.setEmailAddress(customerdto.getEmailAddress());
		repo.saveAndFlush(customer);
		return customerdto;
	}

	@Override
	public void deleteCustomer(int customerid) throws CustomerNotFoundException {
		if (!repo.existsById(customerid))
			throw new CustomerNotFoundException("Customer not found with ID: " + customerid);
		repo.deleteById(customerid);
	}

}

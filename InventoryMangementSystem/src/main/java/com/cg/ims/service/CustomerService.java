package com.cg.ims.service;



import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ims.dao.ICustomerRepository;
import com.cg.ims.dao.IOrderRepo;
import com.cg.ims.dao.IShipmentsRepo;
import com.cg.ims.dto.CustomerDto;
import com.cg.ims.dto.CustomerOrders;
import com.cg.ims.dto.CustomerShipment;
import com.cg.ims.dto.ShipmentStatusCountCustomer;
import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
import com.cg.ims.entity.Shipments;

import com.cg.ims.service.interfaces.ICustomerService;
 
@Service
public class CustomerService implements ICustomerService {
 
    @Autowired
    private ICustomerRepository customerRepository;
 
    @Autowired
    private IOrderRepo orderRepository;
 
    @Autowired
    private IShipmentsRepo shipmentRepository;
 
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customers customer = CustomerMapper.toEntity(customerDto);
        Customers savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }
 
    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customers customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
        return CustomerMapper.toDTO(customer);
    }
 
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customers> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public void deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }
 
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customers existingCustomer = customerRepository.findById(customerDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerDto.getCustomerId()));
 
        existingCustomer.setFullName(customerDto.getFullName());
        existingCustomer.setEmailAddress(customerDto.getEmailAddress());
        Customers updatedCustomer = customerRepository.save(existingCustomer);
 
        return CustomerMapper.toDTO(updatedCustomer);
    }
 
    @Override
    public List<CustomerDto> getCustomerByEmail(String email) {
        List<Customers> li = customerRepository.findByEmailAddress(email);
        List<CustomerDto> customerDtos = li.stream()
                .map(customer -> new CustomerDto(
                        customer.getCustomerId(),
                        customer.getEmailAddress(),
                        customer.getFullName()
                ))
                .collect(Collectors.toList());
        
        return customerDtos;
              
    }
 
    @Override
    public List<CustomerDto> getCustomerByName(String name) {
        List<Customers> customers = customerRepository.findByNameContaining(name);
        if (customers.isEmpty()) {
            throw new RuntimeException("No customers found with name: " + name);
        }
        return customers.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<ShipmentStatusCountCustomer> getCustomerCountByStatus() {
        return customerRepository.getCustomerCountByStatus();
    }
 
    @Override
    public CustomerOrders getCustomerOrders(int customerId) {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        List<Orders> orders = orderRepository.findOrdersByCustomerId(customerId);
        return new CustomerOrders(customer, orders);
    }
 
    @Override
    public CustomerShipment getCustomerShipments(int customerId) {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        List<Shipments> shipments = shipmentRepository.findShipmentsByCustomerId(customerId);
        return new CustomerShipment(customer, shipments);
    }
 
    @Override
    public List<CustomerDto> getCustomersWithPendingShipments() {
        return customerRepository.findCustomersWithPendingShipments()
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<CustomerDto> getCustomersWithCompletedOrders() {
        return customerRepository.findCustomersWithCompletedOrders()
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<CustomerDto> getCustomersWithOverdueShipments() {
        return customerRepository.findCustomersWithOverdueShipments()
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<CustomerDto> getCustomersByOrderQuantityRange(int min, int max) {
        return customerRepository.findCustomersByOrderQuantityRange(min, max)
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
 
	
}
 
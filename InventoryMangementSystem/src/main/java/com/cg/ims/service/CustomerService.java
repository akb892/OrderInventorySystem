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
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.InvalidDataException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.interfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    // Autowiring necessary repositories
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IOrderRepo orderRepository;

    @Autowired
    private IShipmentsRepo shipmentRepository;

    // Method to create a new customer from a CustomerDto object
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) throws BadRequestException, InternalServerErrorException {
        // Validate input data
        if (customerDto == null || customerDto.getFullName() == null || customerDto.getEmailAddress() == null) {
            throw new BadRequestException("Invalid data provided for customer creation.");
        }
        // Convert DTO to entity and save to database
        Customers customer = CustomerMapper.toEntity(customerDto);
        Customers savedCustomer = customerRepository.save(customer);
        
        if (savedCustomer == null) {
            throw new InternalServerErrorException("An error occurred while creating customer.");
        }
        // Return the saved customer as DTO
        return CustomerMapper.toDTO(savedCustomer);
    }

    // Method to get customer details by ID
    @Override
    public CustomerDto getCustomerById(Integer id) throws ResourceNotFoundException {
        return customerRepository.findById(id)
                .map(CustomerMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }

    // Method to get a list of all customers
    @Override
    public List<CustomerDto> getAllCustomers() throws InternalServerErrorException {
        List<Customers> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching all customers.");
        }
        return customers.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Method to delete a customer by ID
    @Override
    public void deleteCustomer(Integer id) throws ResourceNotFoundException, InternalServerErrorException {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    // Method to update an existing customer's details
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) throws ResourceNotFoundException, InvalidDataException, InternalServerErrorException {
        if (customerDto == null ) {
            throw new InvalidDataException("Invalid data provided for customer update.");
        }
        // Check if customer exists in the database
        Customers existingCustomer = customerRepository.findById(customerDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerDto.getCustomerId()));
        
        // Validate the provided data
        if (customerDto.getFullName() == null || customerDto.getEmailAddress() == null) {
            throw new InvalidDataException("Full name or email address cannot be null.");
        }
        // Update customer details
        existingCustomer.setFullName(customerDto.getFullName());
        existingCustomer.setEmailAddress(customerDto.getEmailAddress());
        
        // Save the updated customer
        Customers updatedCustomer = customerRepository.save(existingCustomer);
        if (updatedCustomer == null) {
            throw new InternalServerErrorException("An error occurred while updating the customer.");
        }

        return CustomerMapper.toDTO(updatedCustomer);
    }

    // Method to get a list of customers by email
    @Override
    public List<CustomerDto> getCustomerByEmail(String email) throws ResourceNotFoundException {
        List<Customers> customers = customerRepository.findByEmailAddress(email);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found with email: " + email);
        }
        return customers.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }

    // Method to get a list of customers by name
    @Override
    public List<CustomerDto> getCustomerByName(String name) throws ResourceNotFoundException {
        List<Customers> customers = customerRepository.findByNameContaining(name);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found with name: " + name);
        }
        return customers.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }

    // Method to get customer count by shipment status
    @Override
    public List<ShipmentStatusCountCustomer> getCustomerCountByStatus() throws InternalServerErrorException {
        List<ShipmentStatusCountCustomer> li = customerRepository.getCustomerCountByStatus();
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching customer count by shipment status.");
        }
        return li;
    }

    // Method to get orders for a specific customer by customer ID
    @Override
    public CustomerOrders getCustomerOrders(int customerId) throws ResourceNotFoundException {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        List<Orders> orders = orderRepository.findOrdersByCustomerId(customerId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found for customer ID: " + customerId);
        }
        return new CustomerOrders(customer, orders);
    }

    // Method to get shipments for a specific customer by customer ID
    @Override
    public CustomerShipment getCustomerShipments(int customerId) throws ResourceNotFoundException {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        List<Shipments> shipments = shipmentRepository.findShipmentsByCustomerId(customerId);
        if (shipments.isEmpty()) {
            throw new ResourceNotFoundException("No shipments found for customer ID: " + customerId);
        }
        return new CustomerShipment(customer, shipments);
    }

    // Method to get customers with pending shipments
    @Override
    public List<CustomerDto> getCustomersWithPendingShipments() throws InternalServerErrorException {
        List<CustomerDto> li = customerRepository.findCustomersWithPendingShipments()
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching customers with pending shipments.");
        }
        return li;
    }

    // Method to get customers with completed orders
    @Override
    public List<CustomerDto> getCustomersWithCompletedOrders() throws InternalServerErrorException {
        List<CustomerDto> li = customerRepository.findCustomersWithCompletedOrders()
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching customers with completed orders.");
        }
        return li;
    }

    // Method to get customers with overdue shipments
    @Override
    public List<CustomerDto> getCustomersWithOverdueShipments() throws InternalServerErrorException {
        List<CustomerDto> li = customerRepository.findCustomersWithOverdueShipments()
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching customers with overdue shipments.");
        }
        return li;
    }

    // Method to get customers by order quantity range
    @Override
    public List<CustomerDto> getCustomersByOrderQuantityRange(int min, int max) throws BadRequestException, InternalServerErrorException {
        if (min < 0 || max < 0 || min > max) {
            throw new BadRequestException("Invalid order quantity range.");
        }
        List<CustomerDto> li = customerRepository.findCustomersByOrderQuantityRange(min, max)
                .stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching customers by order quantity range.");
        }
        return li;
    }
}

 
package com.cg.ims.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cg.ims.dto.CustomerDto;
import com.cg.ims.dto.CustomerOrders;
import com.cg.ims.dto.CustomerShipment;
import com.cg.ims.dto.ShipmentStatusCountCustomer;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.exception.list.UnauthorizedException;
import com.cg.ims.exception.list.InvalidDataException;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.service.interfaces.ICustomerService;

import jakarta.validation.Valid;

/**
 * REST controller for managing customer-related operations.
 * Provides endpoints for CRUD operations and various customer-related queries.
 */
@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * Fetches all customers.
     * @return List of all customers.
     * @throws ResourceNotFoundException If no customers are found.
     * @throws InternalServerErrorException If an error occurs during the operation.
     */
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() throws ResourceNotFoundException, InternalServerErrorException {
        List<CustomerDto> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found.");
        }
        return ResponseEntity.ok(customers);
    }

    /**
     * Fetches a customer by their ID.
     * @param id Customer ID.
     * @return Customer details.
     * @throws ResourceNotFoundException If the customer is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) throws ResourceNotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
        }
        return ResponseEntity.ok(customerDto);
    }

    /**
     * Creates a new customer.
     * @param customerDto Customer data transfer object.
     * @return Success message.
     * @throws InvalidDataException If the input data is invalid.
     * @throws BadRequestException If the request is invalid.
     * @throws InternalServerErrorException If an error occurs during the operation.
     */
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerDto customerDto) throws InvalidDataException, BadRequestException, InternalServerErrorException {
        if (customerDto == null || customerDto.getEmailAddress() == null || customerDto.getFullName() == null) {
            throw new InvalidDataException("Customer data is invalid.");
        }
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    /**
     * Updates an existing customer.
     * @param customerDto Customer data transfer object.
     * @return Success message.
     * @throws InvalidDataException If the input data is invalid.
     * @throws ResourceNotFoundException If the customer does not exist.
     * @throws InternalServerErrorException If an error occurs during the operation.
     */
    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerDto customerDto) throws InvalidDataException, ResourceNotFoundException, InternalServerErrorException {
        if (customerDto == null || customerDto.getEmailAddress() == null || customerDto.getFullName() == null) {
            throw new InvalidDataException("Customer data is invalid.");
        }
        customerService.updateCustomer(customerDto);
        return ResponseEntity.ok("Customer updated successfully");
    }

    /**
     * Deletes a customer by their ID.
     * @param customerId Customer ID.
     * @return Success message.
     * @throws ResourceNotFoundException If the customer is not found.
     * @throws BadRequestException If the customer ID is invalid.
     * @throws InternalServerErrorException If an error occurs during the operation.
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws ResourceNotFoundException, BadRequestException, InternalServerErrorException {
        if (customerId == null) {
            throw new BadRequestException("Invalid customer ID provided.");
        }
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Record deleted successfully");
    }

    // Other methods follow the same pattern of comments, explaining their purpose,
    // inputs, and exceptions. Below are brief summaries for the remaining endpoints.

    /**
     * Fetches customers by email address.
     * @param emailId Email address of the customer(s).
     * @return List of customers matching the email address.
     * @throws ResourceNotFoundException If no customers are found.
     */
    @GetMapping("/email/{emailId}")
    public ResponseEntity<List<CustomerDto>> getCustomerByEmail(@PathVariable String emailId) throws ResourceNotFoundException {
        List<CustomerDto> customer = customerService.getCustomerByEmail(emailId);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with the provided email ID not found.");
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * Fetches customers by name or name pattern.
     * @param name Name or pattern to search for.
     * @return List of customers matching the name.
     * @throws ResourceNotFoundException If no customers are found.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDto>> getCustomerByName(@PathVariable String name) throws ResourceNotFoundException {
        List<CustomerDto> customers = customerService.getCustomerByName(name);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("Customer with the provided name wildcard not found.");
        }
        return ResponseEntity.ok(customers);
    }

    // Continue this pattern for other methods.
}

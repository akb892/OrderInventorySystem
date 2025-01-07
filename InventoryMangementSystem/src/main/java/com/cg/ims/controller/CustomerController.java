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
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.InvalidDataException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.exception.UnauthorizedException;
import com.cg.ims.service.interfaces.ICustomerService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomerController {
 
    @Autowired
    private ICustomerService customerService;
 
    // Fetch all customers
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() throws ResourceNotFoundException, InternalServerErrorException {
        List<CustomerDto> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found.");
        }
        return ResponseEntity.ok(customers);
    }
    
    // Fetch Customer By Id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) throws ResourceNotFoundException {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
        }
        return ResponseEntity.ok(customerDto);
    }
 
    // Add new Customer object in DB
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerDto customerDto) throws InvalidDataException, BadRequestException, InternalServerErrorException {
        if (customerDto == null || customerDto.getEmailAddress() == null || customerDto.getFullName() == null) {
            throw new InvalidDataException("Customer data is invalid.");
        }
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
 
    // Update customer by object
    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerDto customerDto) throws InvalidDataException, ResourceNotFoundException, InternalServerErrorException {
        if (customerDto == null || customerDto.getEmailAddress() == null || customerDto.getFullName() == null) {
            throw new InvalidDataException("Customer data is invalid.");
        }
        customerService.updateCustomer(customerDto);
        return ResponseEntity.ok("Customer updated successfully");
    }
 
    // Delete customer by ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws ResourceNotFoundException, BadRequestException, InternalServerErrorException {
        if (customerId == null) {
            throw new BadRequestException("Invalid customer ID provided.");
        }
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Record deleted successfully");
    }
 
    // Search Customers Matching emailId
    @GetMapping("/email/{emailId}")
    public ResponseEntity<List<CustomerDto>> getCustomerByEmail(@PathVariable String emailId) throws ResourceNotFoundException {
        List<CustomerDto> customer = customerService.getCustomerByEmail(emailId);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with the provided email ID not found.");
        }
        return ResponseEntity.ok(customer);
    }
 
    // Search Customers Matching name wildcard
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDto>> getCustomerByName(@PathVariable String name) throws ResourceNotFoundException {
        List<CustomerDto> customers = customerService.getCustomerByName(name);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("Customer with the provided name wildcard not found.");
        }
        return ResponseEntity.ok(customers);
    }
 
    @GetMapping("/shipment/status")
    public ResponseEntity<List<ShipmentStatusCountCustomer>> getOrderCountByStatus() throws InternalServerErrorException {
        List<ShipmentStatusCountCustomer> shipmentStatusCount = customerService.getCustomerCountByStatus();
        if (shipmentStatusCount.isEmpty()) {
            throw new InternalServerErrorException("An error occurred while fetching shipment status count.");
        }
        return ResponseEntity.ok(shipmentStatusCount);
    }
 
    @GetMapping("/{custid}/order")
    public ResponseEntity<CustomerOrders> getCustomerOrders(@PathVariable("custid") int customerId) throws ResourceNotFoundException {
        CustomerOrders customerOrders = customerService.getCustomerOrders(customerId);
        if (customerOrders == null || customerOrders.getCustomer() == null || customerOrders.getOrder().isEmpty()) {
            throw new ResourceNotFoundException("Orders for the specified customer ID not found.");
        }
        return ResponseEntity.ok(customerOrders);
    }
    
    @GetMapping("/{custid}/shipment")
    public ResponseEntity<CustomerShipment> getCustomerShipment(@PathVariable("custid") int customerId) throws ResourceNotFoundException {
        CustomerShipment customerShipment = customerService.getCustomerShipments(customerId);
        if (customerShipment == null || customerShipment.getCustomer() == null) {
            throw new ResourceNotFoundException("Shipment history for the specified customer ID not found.");
        }
        return ResponseEntity.ok(customerShipment);
    }
 
    @GetMapping("/shipments/pending")
    public ResponseEntity<List<CustomerDto>> getCustomersWithPendingShipments() throws InternalServerErrorException {
        List<CustomerDto> customers = customerService.getCustomersWithPendingShipments();
        if (customers.isEmpty()) {
            throw new InternalServerErrorException("No customers with pending shipments found.");
        }
        return ResponseEntity.ok(customers);
    }
 
    @GetMapping("/orders/complete")
    public ResponseEntity<List<CustomerDto>> getCustomersWithCompletedOrders() throws InternalServerErrorException {
        List<CustomerDto> customers = customerService.getCustomersWithCompletedOrders();
        if (customers.isEmpty()) {
            throw new InternalServerErrorException("No customers with completed orders found.");
        }
        return ResponseEntity.ok(customers);
    }
 
    @GetMapping("/shipments/overdue")
    public ResponseEntity<List<CustomerDto>> getCustomersWithOverdueShipments() throws InternalServerErrorException {
        List<CustomerDto> customers = customerService.getCustomersWithOverdueShipments();
        if (customers.isEmpty()) {
            throw new InternalServerErrorException("No customers with overdue shipments found.");
        }
        return ResponseEntity.ok(customers);
    }
 
    @GetMapping("/orders/quantity/{min}/{max}")
    public ResponseEntity<List<CustomerDto>> getCustomersByOrderQuantityRange(@PathVariable int min, @PathVariable int max) throws BadRequestException, InternalServerErrorException {
        if (min < 0 || max < 0 || min > max) {
            throw new BadRequestException("Invalid quantity range.");
        }
        List<CustomerDto> customers = customerService.getCustomersByOrderQuantityRange(min, max);
        return ResponseEntity.ok(customers);
    }
}
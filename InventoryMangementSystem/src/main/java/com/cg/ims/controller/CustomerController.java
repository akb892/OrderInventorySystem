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
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<CustomerDto> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An internal server error occurred while fetching customers.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) {
        try {
            // Call service to get the customer by ID
            CustomerDto customerDto = customerService.getCustomerById(id);
            return ResponseEntity.ok(customerDto); // Return the customer data
        } catch (RuntimeException e) {
            // Handle case where customer is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Return a 404 with null body
        }
    }
 
    // Add new Customer object in DB
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        try {
            CustomerDto createdCustomer = customerService.createCustomer(customerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request. Please provide valid customer data.");
        }
    }
 
    // Update customer by object
    @PutMapping()
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerDto customerDto) {
        try {
            // Pass the DTO and ID to the service for updating the customer
            CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
            return ResponseEntity.ok("Customer updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request. Please provide valid customer data.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the customer.");
        }
    }
 
    // Delete customer by ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok("Record deleted Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request. Please provide valid customer ID for deletion.");
        }
    }
 
    // Search Customers Matching emailId
    @GetMapping("/email/{emailId}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String emailId) {
        try {
            List<CustomerDto> customer = customerService.getCustomerByEmail(emailId);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with the provided email ID not found.");
        }
    }
 
    // Search Customers Matching name wildcard
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCustomerByName(@PathVariable String name) {
        try {
            List<CustomerDto> customers = customerService.getCustomerByName(name);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with the provided name wildcard not found.");
        }
    }
 
    
    @GetMapping("/shipment/status")
	public ResponseEntity<List<ShipmentStatusCountCustomer>> getOrderCountByStatus() throws InternalServerErrorException {
		List<ShipmentStatusCountCustomer> shipmentStatusCount = customerService.getCustomerCountByStatus();
 
		if (shipmentStatusCount.isEmpty()) {
			throw new InternalServerErrorException(
					"An internal server error occurred while fetching shipment status count.");
		}
 
		return new ResponseEntity<List<ShipmentStatusCountCustomer>>(shipmentStatusCount, HttpStatus.OK);
	}
 
    @GetMapping("/{custid}/order")
	public ResponseEntity<CustomerOrders> getCustomerOrders(@PathVariable("custid") int customerId)
			throws ResourceNotFoundException {
		CustomerOrders customerOrders = customerService.getCustomerOrders(customerId);
		if (customerOrders.getCustomer() == null && customerOrders.getOrder().isEmpty()) {
			throw new ResourceNotFoundException("Orders for the specified customer ID not found.");
		}
		return ResponseEntity.ok(customerOrders);
	}
    @GetMapping("/{custid}/shipment")
	public ResponseEntity<CustomerShipment> getCustomerShipment(@PathVariable("custid") int customerId)
			throws ResourceNotFoundException {
		CustomerShipment customerShipment = customerService.getCustomerShipments(customerId);
		if (customerShipment.getCustomer() == null) {
			throw new ResourceNotFoundException("Shipment history for the specified customer ID not found.");
		}
		return ResponseEntity.ok(customerShipment);
	}
 
    @GetMapping("/shipments/pending")
    public ResponseEntity<List<CustomerDto>> getCustomersWithPendingShipments() {
        try {
            return ResponseEntity.ok(customerService.getCustomersWithPendingShipments());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/orders/complete")
    public ResponseEntity<List<CustomerDto>> getCustomersWithCompletedOrders() {
        try {
            return ResponseEntity.ok(customerService.getCustomersWithCompletedOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
 
    @GetMapping("/shipments/overdue")
    public ResponseEntity<List<CustomerDto>> getCustomersWithOverdueShipments() {
        try {
            return ResponseEntity.ok(customerService.getCustomersWithOverdueShipments());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
 
    @GetMapping("/orders/quantity/{min}/{max}")
    public ResponseEntity<List<CustomerDto>> getCustomersByOrderQuantityRange(@PathVariable int min, @PathVariable int max) {
        if (min < 0 || max < 0 || min > max) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        try {
            return ResponseEntity.ok(customerService.getCustomersByOrderQuantityRange(min, max));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

 
}
 


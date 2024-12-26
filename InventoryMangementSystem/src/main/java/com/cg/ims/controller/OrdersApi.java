package com.cg.ims.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.InvalidDataException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IOrdersService;

import jakarta.validation.Valid;

// initialize this api class as rest controller
@RestController
@RequestMapping("api/v1/orders")
public class OrdersApi {
	
	// initialized an object of order service interface and create the object
	@Autowired
	private IOrdersService serv;
	
	// using get mapping created an method for api to fetch all orders
	@GetMapping("/getall")
	public ResponseEntity<List<Orders>> fetchAllOrders() throws InternalServerErrorException {
		// created a list of orders to fetch all orders
		List<Orders> o = serv.fetchAllOrders();
		// return the orders with an https status
		return new ResponseEntity<List<Orders>>(o,HttpStatus.OK);
	}
	
	// using post mapping a method to create orders
	@PostMapping("/create")
	public ResponseEntity<String> createNewOrders(@RequestBody @Valid OrdersDto od) throws BadRequestException {
		// calling the method from service class for implementation
		serv.createNewOrders(od);
		// returning the response along with Http status
		return new ResponseEntity<String>("Record created successfully",HttpStatus.OK);
	}
	
	// using put mapping in a method to update orders
	@PutMapping("/update")
	public ResponseEntity<String> updateOrder(@RequestBody @Valid OrdersDto od) throws BadRequestException {
		// calling the method from service class for implementation
		serv.updateOrdersByObject(od);
		// returning the response along with http status
		return new ResponseEntity<String>("Record Updated successfully", HttpStatus.OK);
	}
	
	
	/**
     * Deletes an order by its ID.
     * @param id the ID of the order to delete.
     * @return a ResponseEntity with a success message.
     * @throws ResourceNotFoundException if the order with the given ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) throws ResourceNotFoundException {
        serv.deleteOrder(id);
        return new ResponseEntity<String>("Record deleted successfully", HttpStatus.OK);
    }

    /**
     * Retrieves the count of orders grouped by their status.
     * @return a ResponseEntity with a map containing status and their respective counts.
     * @throws InternalServerErrorException if there is an issue fetching the data.
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Integer>> countOfOrderByStatus() throws InternalServerErrorException {
        Map<String, Integer> m = serv.countOfOrders();
        return new ResponseEntity<Map<String, Integer>>(m, HttpStatus.OK);
    }

    /**
     * Retrieves a list of orders by store name.
     * @param store the name of the store.
     * @return a ResponseEntity containing a list of OrdersDto objects.
     * @throws ResourceNotFoundException if no orders are found for the given store.
     */
    @GetMapping("/{store}")
    public ResponseEntity<List<OrdersDto>> getOrdersByStoreName(@PathVariable String store) throws ResourceNotFoundException {
        List<OrdersDto> li = serv.getOrdersByStoreName(store);
        return new ResponseEntity<List<OrdersDto>>(li, HttpStatus.OK);
    }

    /**
     * Retrieves order details by ID.
     * @param id the ID of the order.
     * @return a ResponseEntity containing the OrdersDto object.
     * @throws ResourceNotFoundException if the order with the given ID is not found.
     */
    @GetMapping("/allorders/{id}")
    public ResponseEntity<OrdersDto> getOrdersById(@PathVariable int id) throws ResourceNotFoundException {
        OrdersDto od = serv.getOrdersDetailsById(id);
        return new ResponseEntity<OrdersDto>(od, HttpStatus.OK);
    }

    /**
     * Retrieves a list of orders for a specific customer by their ID.
     * @param customerid the ID of the customer.
     * @return a ResponseEntity containing a list of OrdersDto objects.
     * @throws ResourceNotFoundException if no orders are found for the given customer.
     */
    @GetMapping("/customer/{customerid}")
    public ResponseEntity<List<OrdersDto>> getOrdersByCustomerId(@PathVariable int customerid) throws ResourceNotFoundException {
        List<OrdersDto> li = serv.getOrdersBySpecificCustomer(customerid);
        return new ResponseEntity<List<OrdersDto>>(li, HttpStatus.OK);
    }

    /**
     * Marks an order as cancelled by its ID.
     * @param id the ID of the order to cancel.
     * @return a ResponseEntity with a success message.
     * @throws ResourceNotFoundException if the order with the given ID is not found.
     */
    @GetMapping("/{id}/orders")
    public ResponseEntity<String> markOrdersAsCancelled(@PathVariable int id) throws ResourceNotFoundException {
        serv.markOrderAsCancelled(id);
        return new ResponseEntity<String>("Successfully marked canceled", HttpStatus.OK);
    }

    /**
     * Retrieves a single order by its ID.
     * @param id the ID of the order.
     * @return a ResponseEntity containing the OrdersDto object.
     * @throws ResourceNotFoundException if the order with the given ID is not found.
     */
    @GetMapping("/singleorder/{orderid}")
    public ResponseEntity<OrdersDto> getSingleOrder(@PathVariable int id) throws ResourceNotFoundException {
        OrdersDto od = serv.getSingleOrderById(id);
        return new ResponseEntity<OrdersDto>(od, HttpStatus.OK);
    }

    /**
     * Retrieves a list of orders by their status.
     * @param status the status of the orders.
     * @return a ResponseEntity containing a list of OrdersDto objects.
     * @throws ResourceNotFoundException if no orders are found with the given status.
     * @throws InvalidDataException 
     * @throws InternalServerErrorException 
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrdersDto>> getOrderByStatus(@PathVariable String status) throws  InvalidDataException, InternalServerErrorException {
        List<OrdersDto> od = serv.getOrdersByStatus(status);
        return new ResponseEntity<List<OrdersDto>>(od, HttpStatus.OK);
    }

    /**
     * Retrieves a list of orders within a specified date range.
     * @param startdate the start date of the range.
     * @param enddate the end date of the range.
     * @return a ResponseEntity containing a list of OrdersDto objects.
     * @throws ResourceNotFoundException if no orders are found within the given date range.
     */
    @GetMapping("/date/{startdate}/{enddate}")
    public ResponseEntity<List<OrdersDto>> getOrderWithinDateRange(
        @PathVariable LocalDateTime startdate,
        @PathVariable LocalDateTime enddate
    ) throws ResourceNotFoundException {
        List<OrdersDto> od = serv.getOrderWithinDateRange(startdate, enddate);
        return new ResponseEntity<List<OrdersDto>>(od, HttpStatus.OK);
    }

    /**
     * Retrieves a list of orders by a customer's email address.
     * @param email the email address of the customer.
     * @return a ResponseEntity containing a list of OrdersDto objects.
     * @throws ResourceNotFoundException if no orders are found for the given customer email.
     * @throws InvalidDataException if the email format is invalid.
     */
    @GetMapping("/customer/{email}")
    public ResponseEntity<List<OrdersDto>> getOrderByCustomerEmail(@PathVariable String email) throws ResourceNotFoundException, InvalidDataException {
        List<OrdersDto> od = serv.getOrderByCustomerEmail(email);
        return new ResponseEntity<List<OrdersDto>>(od, HttpStatus.OK);
    }
	
}

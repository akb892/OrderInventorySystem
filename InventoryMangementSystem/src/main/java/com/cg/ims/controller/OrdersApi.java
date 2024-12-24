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

import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IOrdersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("orders")
public class OrdersApi {
	
	@Autowired
	private IOrdersService serv;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Orders>> fetchAllOrders() throws InternalServerErrorException {
		List<Orders> o = serv.fetchAllOrders();
		return new ResponseEntity<List<Orders>>(o,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createNewOrders(@RequestBody @Valid OrdersDto od) throws BadRequestException {
		serv.createNewOrders(od);
		return new ResponseEntity<String>("Record created successfully",HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateOrder(@RequestBody @Valid OrdersDto od) throws BadRequestException {
		serv.updateOrdersByObject(od);
		return new ResponseEntity<String>("Record Updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id) throws ResourceNotFoundException{
		serv.deleteOrder(id);
		return new ResponseEntity<String>("Record deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<Map<String, Integer>> countOfOrderByStatus() throws InternalServerErrorException{
		Map<String,Integer> m = serv.countOfOrders();
		return new ResponseEntity<Map<String,Integer>>(m,HttpStatus.OK);
	}
	
	@GetMapping("/{store}")
	public ResponseEntity<List<OrdersDto>> getOrdersByStoreName(@PathVariable String store) throws ResourceNotFoundException{
		List<OrdersDto> li = serv.getOrdersByStoreName(store);
		return new ResponseEntity<List<OrdersDto>>(li,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdersDto> getOrdersById(@PathVariable int id) throws ResourceNotFoundException {
		OrdersDto od = serv.getOrdersDetailsById(id);
		return new ResponseEntity<OrdersDto>(od, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerid}")
	public ResponseEntity<List<OrdersDto>> getOrdersByCustomerId(@PathVariable int id) throws ResourceNotFoundException {
		List<OrdersDto> li = serv.getOrdersBySpecificCustomer(id);
		return new ResponseEntity<List<OrdersDto>>(li, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/orders")
	public ResponseEntity<String> markOrdersAsCancelled(@PathVariable int id) throws ResourceNotFoundException{
		serv.markOrderAsCancelled(id);
		return new ResponseEntity<String>("Successfully marked canceled", HttpStatus.OK);
	}
	
	@GetMapping("/{orderid}")
	public ResponseEntity<OrdersDto> getSingleOrder(@PathVariable int id) throws ResourceNotFoundException{
		OrdersDto od = serv.getSingleOrderById(id);
		return new ResponseEntity<OrdersDto>(od, HttpStatus.OK);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<OrdersDto>> getOrderByStatus(@PathVariable String status) throws ResourceNotFoundException{
		List<OrdersDto> od = serv.getOrdersByStatus(status);
		return new ResponseEntity<List<OrdersDto>>(od, HttpStatus.OK);
	}
	
	@GetMapping("/date/{startdate}/{enddate}")
	public ResponseEntity<List<OrdersDto>> getOrderWithinDateRange(@PathVariable LocalDateTime startdate, @PathVariable LocalDateTime enddate) throws ResourceNotFoundException{
		List<OrdersDto> od =  serv.getOrderWithinDateRange(startdate, enddate);
		return new ResponseEntity<List<OrdersDto>>(od, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<List<OrdersDto>> getOrderByCustomerEmail(@PathVariable String email) throws ResourceNotFoundException{
		List<OrdersDto> od = serv.getOrderByCustomerEmail(email);
		return new ResponseEntity<List<OrdersDto>>(od, HttpStatus.OK);
	}
	
}

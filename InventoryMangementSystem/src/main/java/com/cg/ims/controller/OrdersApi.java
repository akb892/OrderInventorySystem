package com.cg.ims.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.service.interfaces.IOrdersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("orders")
public class OrdersApi {
	
	@Autowired
	private IOrdersService serv;
	
	@GetMapping
	public ResponseEntity<List<Orders>> fetchAllOrders() {
		List<Orders> o = serv.fetchAllOrders();
		return new ResponseEntity<List<Orders>>(o,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createNewOrders(@RequestBody @Valid OrdersDto od) {
		serv.createNewOrders(od);
		return new ResponseEntity<String>("Record created successfully",HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateOrder(@RequestBody @Valid OrdersDto od) {
		serv.updateOrdersByObject(od);
		return new ResponseEntity<String>("Record Updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(int id){
		serv.deleteOrder(id);
		return new ResponseEntity<String>("Record deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<Map<String, Integer>> countOfOrderByStatus(){
		Map<String,Integer> m = serv.countOfOrders();
		return new ResponseEntity<Map<String,Integer>>(m,HttpStatus.OK);
	}
	
}

package com.cg.ims.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;

public interface IOrdersService {

	List<Orders> fetchAllOrders() throws InternalServerErrorException;
	OrdersDto createNewOrders(OrdersDto od) throws BadRequestException;
	void updateOrdersByObject(OrdersDto od) throws BadRequestException;
	String deleteOrder(int id) throws ResourceNotFoundException;
	Map<String, Integer> countOfOrders() throws InternalServerErrorException;
	List<OrdersDto> getOrdersByStoreName(String storeName) throws ResourceNotFoundException;
	OrdersDto getOrdersDetailsById(int id) throws ResourceNotFoundException;
	List<OrdersDto> getOrdersBySpecificCustomer(int customerId) throws ResourceNotFoundException;
	void markOrderAsCancelled(int id) throws ResourceNotFoundException;
	OrdersDto getSingleOrderById(int orderId) throws ResourceNotFoundException;
	List<OrdersDto> getOrdersByStatus(String status) throws ResourceNotFoundException;
	List<OrdersDto> getOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) throws ResourceNotFoundException;
	List<OrdersDto> getOrderByCustomerEmail(String email) throws ResourceNotFoundException;
	
	
	
	
	
}

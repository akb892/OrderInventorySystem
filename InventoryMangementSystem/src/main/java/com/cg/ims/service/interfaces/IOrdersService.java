package com.cg.ims.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.exception.CustomerNotFoundException;
import com.cg.ims.exception.OrderAlreadyExistsException;
import com.cg.ims.exception.OrdersNotFoundException;

public interface IOrdersService {

	List<Orders> fetchAllOrders();
	OrdersDto createNewOrders(OrdersDto od) throws OrderAlreadyExistsException;
	void updateOrdersByObject(OrdersDto od);
	String deleteOrder(int id);
	Map<String, Integer> countOfOrders();
	List<OrdersDto> getOrdersByStoreName(String storeName);
	OrdersDto getOrdersDetailsById(int id) throws OrdersNotFoundException;
	List<OrdersDto> getOrdersBySpecificCustomer(int customerId) throws CustomerNotFoundException;
	void markOrderAsCancelled(int id);
	OrdersDto getSingleOrderById(int orderId) throws OrdersNotFoundException;
	List<OrdersDto> getOrdersByStatus(String status);
	List<OrdersDto> getOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);
	List<OrdersDto> getOrderByCustomerEmail(String email);
	
	
	
	
	
}

package com.cg.ims.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cg.ims.dto.OrdersDto;
import com.cg.ims.service.interfaces.IOrdersService;

@Service
public class OrderService implements IOrdersService {

	@Override
	public List<OrdersDto> fetchAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewOrders(OrdersDto od) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrdersByObject(OrdersDto od) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Integer> countOfOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDto getOrdersByStoreName(String storeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDto getOrdersDetailsById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersDto> getOrdersBySpecificCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markOrderAsCancelled(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrdersDto getSingleOrderById(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersDto> getOrdersByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersDto> getOrderWithinDateRange(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersDto getOrderByCustomerEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

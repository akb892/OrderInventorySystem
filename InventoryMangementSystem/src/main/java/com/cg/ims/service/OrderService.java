package com.cg.ims.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ims.dao.IOrderRepo;
import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.service.interfaces.IOrdersService;

@Service
public class OrderService implements IOrdersService {

	@Autowired
	private IOrderRepo repo;

	@Override
	public List<Orders> fetchAllOrders() {
		return repo.findAll();
	}

	@Override
	public OrdersDto createNewOrders(OrdersDto od) {
		// TODO Auto-generated method stub
		Orders o = new Orders();
		Optional<Orders> op = repo.findById(od.getOrderID());
		if (op.isPresent()) {
			return null;
		} else {
			o.setCustomer(od.getCustomer());
			o.setOi(od.getOi());
			o.setOrderID(od.getOrderID());
			o.setOrderStatus(od.getOrderStatus());
			o.setOrderTms(od.getOrderTms());
			o.setStore(od.getStore());
			repo.saveAndFlush(o);
			return od;
		}
	}

	@Override
	public void updateOrdersByObject(OrdersDto od) {
		// TODO Auto-generated method stub
		Orders o = new Orders();
		o.setCustomer(od.getCustomer());
		o.setOi(od.getOi());
		o.setOrderID(od.getOrderID());
		o.setOrderStatus(od.getOrderStatus());
		o.setOrderTms(od.getOrderTms());
		o.setStore(od.getStore());
		Optional<Orders> op = repo.findById(od.getOrderID());
		if(op.isPresent()) {
			repo.saveAndFlush(o);
		}
	}

	@Override
	public String deleteOrder(int id) {
		Optional<Orders> op = repo.findById(id);
		if (op.isPresent()) {
			repo.delete(op.get());
			return "Order Deleted";
		} else {
			return "Order Not found";
		}
	}

	@Override
	public Map<String, Integer> countOfOrders() {
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

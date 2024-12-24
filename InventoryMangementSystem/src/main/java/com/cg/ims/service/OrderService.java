package com.cg.ims.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cg.ims.dao.ICustomerRepo;
import com.cg.ims.dao.IOrderRepo;
import com.cg.ims.dao.IStoreRepo;
import com.cg.ims.dto.CustomersDto;
import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
import com.cg.ims.entity.Stores;

import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IOrdersService;


@Service
public class OrderService implements IOrdersService {

	@Autowired
	private IOrderRepo repo;
	@Autowired
	private ICustomerRepo repo1;
	@Autowired
	private IStoreRepo repo2;

	@Override
	public List<Orders> fetchAllOrders() throws InternalServerErrorException{
		List<Orders> li = repo.findAll();
		if(li.isEmpty()) {
			throw new InternalServerErrorException("An internal server error occured while fetching all Orders");
		}
		else {
			return li;
		}
	}

	@Override
	public OrdersDto createNewOrders(OrdersDto od) throws BadRequestException {
		// TODO Auto-generated method stub
		Orders o = new Orders();
		Optional<Orders> op = repo.findById(od.getOrderID());
		if (op.isPresent()) {
			throw new BadRequestException("Invalid Request. Please provide Valid order Data for creation");
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
	public void updateOrdersByObject(OrdersDto od) throws BadRequestException {
		// TODO Auto-generated method stub
		Orders o = new Orders();
		Optional<Orders> op = repo.findById(od.getOrderID());
		if(op.isPresent()) {
		o.setCustomer(od.getCustomer());
		o.setOi(od.getOi());
		o.setOrderID(od.getOrderID());
		o.setOrderStatus(od.getOrderStatus());
		o.setOrderTms(od.getOrderTms());
		o.setStore(od.getStore());
		repo.saveAndFlush(o);
		}
		else {
			throw new BadRequestException("Invalid request please provide valid order data for updating");
		}
	}

	@Override
	public String deleteOrder(int id) throws ResourceNotFoundException{
		Optional<Orders> op = repo.findById(id);
		if (op.isPresent()) {
			repo.delete(op.get());
		} else {
			throw new ResourceNotFoundException("Order with the specified id not found for deletion");
		}
		return null;
	}

	@Override
	public Map<String, Integer> countOfOrders() throws InternalServerErrorException{
		List<Object[]> results = repo.countOrdersByStatus();
		if(results.isEmpty()) {
			throw new InternalServerErrorException("An internal server error occurred while fetching the count of orders by status.");
		}
		Map<String , Integer> m = new HashMap<>();
		
		for(Object[] result : results) {
			String status = (String) result[0];
			Integer count = (Integer)result[1];
			m.put(status, count);
		}
		
		return m;
	}

	@Override
	public List<OrdersDto> getOrdersByStoreName(String storeName) throws ResourceNotFoundException {
		List<OrdersDto> od  = new ArrayList<>();
		List<Stores> s = repo2.findByStoreName(storeName);
		for(Stores s1 : s) {
			List<Orders> li = new ArrayList<>(s1.getOi());
			if(li.isEmpty()) {
				throw new ResourceNotFoundException("Orders with the specified store name not found.");
			}
			OrdersDto od1 = new OrdersDto();
			for(Orders o : li) {
				od1.setCustomer(o.getCustomer());
				od1.setOi(o.getOi());
				od1.setOrderID(o.getOrderID());
				od1.setOrderStatus(o.getOrderStatus());
				od1.setOrderTms(o.getOrderTms());
				od1.setStore(o.getStore());
				od.add(od1);
			}
		}
		return od;
	}

	@Override
	public OrdersDto getOrdersDetailsById(int id) throws ResourceNotFoundException{
		List<Orders> o = new ArrayList<>(repo.findByOrderID(id));
		if(o.isEmpty()) {
			throw new ResourceNotFoundException("Order with the specified ID not found.");
		}
		List<OrdersDto> od1 = new ArrayList<>();
		OrdersDto od = new OrdersDto();
		for(Orders o1 : o) {
			od.setCustomer(o1.getCustomer());
			od.setOi(o1.getOi());
			od.setOrderID(o1.getOrderID());
			od.setOrderStatus(o1.getOrderStatus());
			od.setOrderTms(o1.getOrderTms());
			od.setStore(o1.getStore());
			od1.add(od);
		}
		return od;
	}

	@Override
	public List<OrdersDto> getOrdersBySpecificCustomer(int customerId) throws ResourceNotFoundException {
		CustomersDto co = new CustomersDto();
		co.setCustomerId(customerId);
		if(co.getCustomerId()>0) {
			Optional<Customers> op = repo1.findById(customerId);
			if(op.isPresent()) {
				Customers c = op.get();
				List<Orders> o = new ArrayList<>(c.getOrder());
				List<OrdersDto> li = new ArrayList<>();
				OrdersDto od = new OrdersDto();
				for(Orders o1 : o) {
					od.setCustomer(o1.getCustomer());
					od.setOi(o1.getOi());
					od.setOrderID(o1.getOrderID());
					od.setOrderStatus(o1.getOrderStatus());
					od.setOrderTms(o1.getOrderTms());
					od.setStore(o1.getStore());
					li.add(od);
				}
				return li;
			}
			else {
				throw new ResourceNotFoundException("Orders for the specified customer ID not found.");
			}
		}
		else {
			return null;
		}
		
	}

	@Override
	public void markOrderAsCancelled(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		OrdersDto od  = new OrdersDto();
		od.setOrderID(id);
		if(od.getOrderID()>0) {
			Optional<Orders> op = repo.findById(id);
			if(op.isPresent()) {
				Orders o = new Orders();
				o = op.get();
				o.setOrderStatus("Cancelled");
				repo.saveAndFlush(o);
			}
			else {
				throw new ResourceNotFoundException("Order with the specified ID not found for cancellation.");
			}
		}
	}

	@Override
	public OrdersDto getSingleOrderById(int orderId) throws ResourceNotFoundException {
		Optional<Orders> op = repo.findById(orderId);
		if(op.isPresent()) {
			Orders o = op.get();
			OrdersDto od = new OrdersDto();
			od.setCustomer(o.getCustomer());
			od.setOi(o.getOi());
			od.setOrderID(o.getOrderID());
			od.setOrderStatus(o.getOrderStatus());
			od.setOrderTms(o.getOrderTms());
			od.setStore(o.getStore());
			return od;
		}
		else {
			throw new ResourceNotFoundException("Order with the specified order ID not found.");
		}
	}

	@Override
	public List<OrdersDto> getOrdersByStatus(String status) throws ResourceNotFoundException {
		List<OrdersDto> li = new ArrayList<>();
		OrdersDto od = new OrdersDto();
		od.setOrderStatus(status);
		if(od.getOrderStatus() != null) {
			List<Orders> o = repo.findByOrderStatus(status);
			if(o.isEmpty()) {
				throw new ResourceNotFoundException("Orders with the specified status not found.");
			}
			for(Orders or : o) {
				od.setCustomer(or.getCustomer());
				od.setOi(or.getOi());
				od.setOrderID(or.getOrderID());
				od.setOrderStatus(or.getOrderStatus());
				od.setOrderTms(or.getOrderTms());
				od.setStore(or.getStore());
				li.add(od);
			}
			return li;
		}
		return null;
	}

	@Override
	public List<OrdersDto> getOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) throws ResourceNotFoundException {
		Assert.notNull(startDate, "Start Date cannot be null");
		Assert.notNull(endDate, "End Date cannot be null");
		OrdersDto od = new OrdersDto();
		List<OrdersDto> li = new ArrayList<>();
		if(startDate.isBefore(endDate)) {
			List<Orders> o = repo.findOrderWithinDateRange(startDate, endDate);
			if(o.isEmpty()) {
				throw new ResourceNotFoundException("Orders within the specified date range not found.");
			}
			for(Orders or : o) {
				od.setCustomer(or.getCustomer());
				od.setOi(or.getOi());
				od.setOrderID(or.getOrderID());
				od.setOrderStatus(or.getOrderStatus());
				od.setOrderTms(or.getOrderTms());
				od.setStore(or.getStore());
				li.add(od);
			}
			return li;
		}
		else {
			return null;
		}
	}

	@Override
	public List<OrdersDto> getOrderByCustomerEmail(String email) throws ResourceNotFoundException {
		List<OrdersDto> od = new ArrayList<>();
		List<Customers> c = repo1.findByEmailAddress(email);
		for(Customers c1 : c) {
			List<Orders> o = new ArrayList<>(c1.getOrder());
			if(o.isEmpty()) {
				throw new ResourceNotFoundException("Orders for the specified customer email not found.");
			}
			OrdersDto od1 = new OrdersDto();
			for(Orders o1 : o) {
				od1.setCustomer(o1.getCustomer());
				od1.setOi(o1.getOi());
				od1.setOrderID(o1.getOrderID());
				od1.setOrderStatus(o1.getOrderStatus());
				od1.setOrderTms(o1.getOrderTms());
				od1.setStore(o1.getStore());
				od.add(od1);
			}
		}
		return od;
	}

}

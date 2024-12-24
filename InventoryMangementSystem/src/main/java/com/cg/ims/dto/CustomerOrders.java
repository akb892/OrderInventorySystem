package com.cg.ims.dto;

import java.util.List;

import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
 
public class CustomerOrders {
	private Customers customer;
	private List<Orders> order;
	public CustomerOrders() {
 
}
 
	public CustomerOrders(Customers customer, List<Orders> order) {
		super();
		this.customer = customer;
		this.order = order;
	}
 
	public Customers getCustomer() {
		return customer;
	}
 
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
 
	public List<Orders> getOrder() {
		return order;
	}
 
	public void setOrder(List<Orders> order) {
		this.order = order;
	}
 
}	


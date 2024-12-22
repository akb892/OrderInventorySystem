package com.cg.ims.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.ims.entity.Customers;
import com.cg.ims.entity.OrderItems;
import com.cg.ims.entity.Stores;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrdersDto {
	
	
	@NotNull(message = "Order id cannot be null")
	@NotBlank(message = "Order id cannot be blank")
	private int orderID;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime orderTms;
	
	
	private Customers customer;
	
	
	@Size(max = 10, message = "Order status must not exceed 10 characters")
	private String orderStatus;
	
	private Stores store;
	
	
	private List<OrderItems> oi;
	
	
	public OrdersDto() {
		// TODO Auto-generated constructor stub
	}


	public OrdersDto(
			@NotNull(message = "Order id cannot be null") @NotBlank(message = "Order id cannot be blank") int orderID,
			LocalDateTime orderTms, Customers customer,
			@Size(max = 10, message = "Order status must not exceed 10 characters") String orderStatus, Stores store,
			List<OrderItems> oi) {
		super();
		this.orderID = orderID;
		this.orderTms = orderTms;
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.store = store;
		this.oi = oi;
	}


	public int getOrderID() {
		return orderID;
	}


	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public LocalDateTime getOrderTms() {
		return orderTms;
	}


	public void setOrderTms(LocalDateTime orderTms) {
		this.orderTms = orderTms;
	}


	public Customers getCustomer() {
		return customer;
	}


	public void setCustomer(Customers customer) {
		this.customer = customer;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Stores getStore() {
		return store;
	}


	public void setStore(Stores store) {
		this.store = store;
	}


	public List<OrderItems> getOi() {
		return oi;
	}


	public void setOi(List<OrderItems> oi) {
		this.oi = oi;
	}
	
	
	
}

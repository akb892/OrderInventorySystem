package com.cg.ims.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "orders")
public class Orders {
	
	
	@Id
	@Column(name = "order_id")
	private int orderID;
	
	@Column(name = "order_tms")
	private LocalDateTime orderTms;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customers customer;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Stores store;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItems> oi;
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Orders(int orderID, LocalDateTime orderTms, Customers customer, String orderStatus, Stores store,
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

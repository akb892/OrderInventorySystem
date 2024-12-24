package com.cg.ims.entity;

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
@Table(name = "shipments")
public class Shipments {
 
	
	@Id
	@Column(name = "shipment_id")
	private int shipmentId;
	@ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Stores store;
 
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;
 
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;
	@Column(name = "delivery_address")
	private String deliveryAddress;
	@Column(name = "shipment_status")
	private String shipmentStatus;
 
	public int getShipmentId() {
		return shipmentId;
	}
 
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
 
	public Stores getStore() {
		return store;
	}
 
	public void setStore(Stores store) {
		this.store = store;
	}
 
	public Customers getCustomer() {
		return customer;
	}
 
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
 
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
 
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
 
	public String getShipmentStatus() {
		return shipmentStatus;
	}
 
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
}


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
	private int shipmentsId;
	
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Stores store;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customers customer;
	
	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Column(name = "shipmemnt_status")
	private String shipmentStatus;

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	private List<OrderItems> oi;

	public Shipments(int shipmentsId, Stores store, Customers customer, String deliveryAddress, String shipmentStatus,
			List<OrderItems> oi) {
		super();
		this.shipmentsId = shipmentsId;
		this.store = store;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.shipmentStatus = shipmentStatus;
		this.oi = oi;
	}

	public int getShipmentsId() {
		return shipmentsId;
	}

	public void setShipmentsId(int shipmentsId) {
		this.shipmentsId = shipmentsId;
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

	public List<OrderItems> getOi() {
		return oi;
	}

	public void setOi(List<OrderItems> oi) {
		this.oi = oi;
	}
	
	
	
}
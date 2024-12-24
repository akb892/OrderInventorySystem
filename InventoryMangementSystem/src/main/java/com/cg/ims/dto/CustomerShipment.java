package com.cg.ims.dto;

import java.util.List;

import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Shipments;
 
public class CustomerShipment {
	private Customers customer;
	private List<Shipments> shipment;
 
	public CustomerShipment() {
	}
 
	public CustomerShipment(Customers customer, List<Shipments> shipment) {
		super();
		this.customer = customer;
		this.shipment = shipment;
	}
 
	public Customers getCustomer() {
		return customer;
	}
 
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
 
	public List<Shipments> getShipment() {
		return shipment;
	}
 
	public void setShipment(List<Shipments> shipment) {
		this.shipment = shipment;
	}

}


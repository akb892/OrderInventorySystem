package com.cg.ims.dto;

public class ShipmentStatusCountCustomer {
	private String shipmentStatus;
	private int count;
	
	
	public ShipmentStatusCountCustomer(String shipmentStatus, int count) {
		super();
		this.shipmentStatus = shipmentStatus;
		this.count = count;
	}
	public ShipmentStatusCountCustomer() {
		
	}
	public String getShipmentStatus() {
		return shipmentStatus;
	}
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
 
}
 
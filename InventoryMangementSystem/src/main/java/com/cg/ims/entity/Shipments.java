package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "shipments")
public class Shipments {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Shipments(Stores store, Customers customer, String deliveryAddress, String shipmentStatus) {
		super();
		this.store = store;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
		this.shipmentStatus = shipmentStatus;
	}
	
	
	
}

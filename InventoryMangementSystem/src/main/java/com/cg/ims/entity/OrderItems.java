package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
public class OrderItems {
	
	
	@ManyToOne
	@JoinColumn(name= "order_id", referencedColumnName = "order_id")
	private Orders order;
	
	@Column(name = "line_item_id")
	private int lineItemId;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Products product;
	
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
	private Shipments shipment;
	
	
	public OrderItems() {
		// TODO Auto-generated constructor stub
	}

	public OrderItems(Orders order, int lineItemId, Products product, Double unitPrice, int quantity,
			Shipments shipment) {
		super();
		this.order = order;
		this.lineItemId = lineItemId;
		this.product = product;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.shipment = shipment;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public int getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(int lineItemId) {
		this.lineItemId = lineItemId;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Shipments getShipment() {
		return shipment;
	}

	public void setShipment(Shipments shipment) {
		this.shipment = shipment;
	}
	
	
}
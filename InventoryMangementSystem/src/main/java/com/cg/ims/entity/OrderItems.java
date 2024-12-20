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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@ManyToOne()
	@JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
	private Shipments shipment;
}

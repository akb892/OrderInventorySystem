package com.cg.ims.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "orders")
public class Orders {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

}

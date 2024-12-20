package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Column(name = "colour")
	private String color;
	
	@Column(name = "brand")
	private String brand;
	
	
	@Column(name = "size")
	private String size;
	
	
	@Column(name = "rating")
	private int rating;


	public Products(String productName, Double unitPrice, String color, String brand, String size, int rating) {
		super();
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.color = color;
		this.brand = brand;
		this.size = size;
		this.rating = rating;
	}
	
	
	
}

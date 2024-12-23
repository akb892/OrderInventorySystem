package com.cg.ims.entity;
 
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "products")
public class Products {
 
	@Id
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
 
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;
 
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Inventory> inventories;
 
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}
 
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
 
	public List<Inventory> getInventories() {
		return inventories;
	}
 
	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}
 
	public int getProductId() {
		return productId;
	}
 
	public void setProductId(int productId) {
		this.productId = productId;
	}
 
	public String getProductName() {
		return productName;
	}
 
	public void setProductName(String productName) {
		this.productName = productName;
	}
 
	public Double getUnitPrice() {
		return unitPrice;
	}
 
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
 
	public String getColor() {
		return color;
	}
 
	public void setColor(String color) {
		this.color = color;
	}
 
	public String getBrand() {
		return brand;
	}
 
	public void setBrand(String brand) {
		this.brand = brand;
	}
 
	public String getSize() {
		return size;
	}
 
	public void setSize(String size) {
		this.size = size;
	}
 
	public int getRating() {
		return rating;
	}
 
	public void setRating(int rating) {
		this.rating = rating;
	}
 
	public Products(int productId, String productName, Double unitPrice, String color, String brand, String size,
			int rating) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.color = color;
		this.brand = brand;
		this.size = size;
		this.rating = rating;
	}
 
	public Products() {
 
	}
 
}
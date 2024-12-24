package com.cg.ims.dto;
 
 
import java.util.List;

import com.cg.ims.entity.Inventory;
import com.cg.ims.entity.OrderItems;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
 
public class ProductDto {
 
	private Integer productId;
 
	@NotBlank(message = "Product name is mandatory")
	private String productName;
 
	@DecimalMin(value = "0.0", message = "Unit price must be greater than or equal to 0")
	private Double unitPrice;
 
	private String colour;
 
	private String brand;
 
	private String size;
 
	@Min(value = 0, message = "Rating must be greater than or equal to 0")
	@Max(value = 5, message = "Rating must be less than or equal to 5")
	private Integer rating;
	
	
	
	private List<OrderItems> orderItems;
	
	private List<Inventory> inventory;
 
	
	
 
	// Getters and Setters
 
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	public Integer getProductId() {
		return productId;
	}
 
	public void setProductId(Integer productId) {
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
 
	public String getColour() {
		return colour;
	}
 
	public void setColour(String colour) {
		this.colour = colour;
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
 
	public Integer getRating() {
		return rating;
	}
 
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public ProductDto(Integer productId, @NotBlank(message = "Product name is mandatory") String productName,
			@DecimalMin(value = "0.0", message = "Unit price must be greater than or equal to 0") Double unitPrice,
			String colour, String brand, String size,
			@Min(value = 0, message = "Rating must be greater than or equal to 0") @Max(value = 5, message = "Rating must be less than or equal to 5") Integer rating,
			List<OrderItems> orderItems, List<Inventory> inventory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.colour = colour;
		this.brand = brand;
		this.size = size;
		this.rating = rating;
		this.orderItems = orderItems;
		this.inventory = inventory;
	}
 
	
 
	
 
}
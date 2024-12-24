package com.cg.ims.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
 
public class InventoryDto {
    private int inventoryId;
 
    @NotNull(message = "Store ID is required")
    private int storeId;
 
    @NotNull(message = "Product ID is required")
    private int productId;
 
    @NotNull(message = "Product inventory is required")
    @PositiveOrZero(message = "Product inventory cannot be negative")
    private Integer productInventory;
 
	public int getInventoryId() {
		return inventoryId;
	}
 
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
 
	public int getStoreId() {
		return storeId;
	}
 
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
 
	public int getProductId() {
		return productId;
	}
 
	public void setProductId(int productId) {
		this.productId = productId;
	}
 
	public Integer getProductInventory() {
		return productInventory;
	}
 
	public void setProductInventory(Integer productInventory) {
		this.productInventory = productInventory;
	}
    
 
}
 

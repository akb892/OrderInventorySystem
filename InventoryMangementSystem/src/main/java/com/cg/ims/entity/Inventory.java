package com.cg.ims.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {
	
	
	@Id
	private int inventoryId;
	private int storeId;
	private int productId;
	private int productInventory;
	
	
	public Inventory() {
		// TODO Auto-generated constructor stub
	}


	public Inventory(int inventoryId, int storeId, int productId, int productInventory) {
		super();
		this.inventoryId = inventoryId;
		this.storeId = storeId;
		this.productId = productId;
		this.productInventory = productInventory;
	}


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


	public int getProductInventory() {
		return productInventory;
	}


	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}
	
	
	
	
}

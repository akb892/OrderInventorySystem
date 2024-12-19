package com.cg.ims.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {
	
	
	@Id
	private int inventoryId;
	private int productInventory;
	
	
	public Inventory() {
		// TODO Auto-generated constructor stub
	}


	public Inventory(int inventoryId,int productInventory) {
		super();
		this.inventoryId = inventoryId;
		this.productInventory = productInventory;
	}


	public int getInventoryId() {
		return inventoryId;
	}


	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}




	public int getProductInventory() {
		return productInventory;
	}


	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}
	
	
	
	
}

package com.cg.ims.entity;
 
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

 
@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	private int inventoryId;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @JsonIgnore
    private Stores store;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Products product;
	@Column(name = "product_inventory")
	private int productInventory;
 
	public Inventory() {
	}
	public Inventory(int inventoryId, Stores store, Products product, int productInventory) {
		super();
		this.inventoryId = inventoryId;
		this.store = store;
		this.product = product;
		this.productInventory = productInventory;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Stores getStore() {
		return store;
	}
	public void setStore(Stores store) {
		this.store = store;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public int getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}	
}

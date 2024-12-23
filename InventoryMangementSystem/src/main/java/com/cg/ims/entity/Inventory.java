package com.cg.ims.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	private int inventoryId;
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Stores store;
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Products product;
	@Column(name = "product_inventory")
	private int productInventory;
 
	public Inventory() {
		// TODO Auto-generated constructor stub
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

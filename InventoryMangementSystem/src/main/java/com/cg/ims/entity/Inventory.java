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
@Table(name = "inventory") // Maps this entity to the "inventory" table in the database
public class Inventory {

    // Unique identifier for the inventory entry (primary key)
    @Id
    private int inventoryId;

    // Many-to-one relationship with the "Stores" entity
    // This defines that each inventory entry is associated with a specific store
    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetching to load store data only when needed
    @JoinColumn(name = "store_id", nullable = false) // Maps to "store_id" column in the inventory table
    @JsonIgnore // Ignores this field in JSON serialization to avoid circular references
    private Stores store;

    // Many-to-one relationship with the "Products" entity
    // This defines that each inventory entry is associated with a specific product
    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetching to load product data only when needed
    @JoinColumn(name = "product_id", nullable = false) // Maps to "product_id" column in the inventory table
    @JsonIgnore // Ignores this field in JSON serialization to avoid circular references
    private Products product;

    // Quantity of the product available in the store
    @Column(name = "product_inventory") // Maps to the "product_inventory" column in the database
    private int productInventory;

    // Default constructor for initializing the inventory object
    public Inventory() {
    }

    // Constructor with parameters to initialize the inventory object with values
    public Inventory(int inventoryId, Stores store, Products product, int productInventory) {
        super();
        this.inventoryId = inventoryId;
        this.store = store;
        this.product = product;
        this.productInventory = productInventory;
    }

    // Getter and setter methods for each field

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

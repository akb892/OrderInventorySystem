package com.cg.ims.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing a Product in the inventory system.
 * Maps to the "products" table in the database.
 */
@Entity
@Table(name = "products")
public class Products {

    /**
     * Unique identifier for each product.
     */
    @Id
    @Column(name = "product_id")
    private int productId;

    /**
     * Name of the product.
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * Unit price of the product.
     */
    @Column(name = "unit_price")
    private Double unitPrice;

    /**
     * Color of the product.
     */
    @Column(name = "colour")
    private String color;

    /**
     * Brand of the product.
     */
    @Column(name = "brand")
    private String brand;

    /**
     * Size of the product (e.g., dimensions, weight, or other size attributes).
     */
    @Column(name = "size")
    private String size;

    /**
     * Rating of the product (e.g., customer reviews).
     */
    @Column(name = "rating")
    private int rating;

    /**
     * List of order items associated with the product.
     * Establishes a one-to-many relationship with the `OrderItems` entity.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    /**
     * List of inventory entries associated with the product.
     * Establishes a one-to-many relationship with the `Inventory` entity.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    // Getters and setters for accessing and modifying the fields.

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

    /**
     * Parameterized constructor to initialize a Product with specific details.
     *
     * @param productId   Unique identifier for the product.
     * @param productName Name of the product.
     * @param unitPrice   Unit price of the product.
     * @param color       Color of the product.
     * @param brand       Brand of the product.
     * @param size        Size of the product.
     * @param rating      Rating of the product.
     */
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

    /**
     * Default constructor for creating an empty Product instance.
     */
    public Products() {
        // Default constructor
    }
}

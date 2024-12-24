package com.cg.ims.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing the order items in the system.
 * Each order item is uniquely identified by a composite key consisting of the
 * order and product.
 */
@Entity
@IdClass(CompositeKeyClass.class) // Specifies that this entity uses a composite key.
@Table(name = "order_items") // Maps this entity to the "order_items" table in the database.
public class OrderItems {

    /**
     * Represents the order associated with the order item.
     * Part of the composite key.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders order;

    /**
     * Unique identifier for the line item within an order.
     */
    @Column(name = "line_item_id")
    private int lineItemId;

    /**
     * Represents the product associated with the order item.
     * Part of the composite key.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Products product;

    /**
     * Unit price of the product in the order item.
     */
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    /**
     * Quantity of the product ordered.
     */
    @Column(name = "quantity")
    private int quantity;

    /**
     * Represents the shipment associated with the order item.
     * This is optional and indicates the shipment details for the product.
     */
    @ManyToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
    private Shipments shipment;

    /**
     * Default constructor.
     * Used for creating an empty instance of OrderItems.
     */
    public OrderItems() {
        // Default constructor
    }

    /**
     * Parameterized constructor for initializing all fields of the OrderItems class.
     * 
     * @param order      The associated order.
     * @param lineItemId Unique identifier for the line item.
     * @param product    The associated product.
     * @param unitPrice  Unit price of the product.
     * @param quantity   Quantity of the product ordered.
     * @param shipment   The associated shipment.
     */
    public OrderItems(Orders order, int lineItemId, Products product, BigDecimal unitPrice, int quantity,
            Shipments shipment) {
        super();
        this.order = order;
        this.lineItemId = lineItemId;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.shipment = shipment;
    }

    // Getter and Setter methods for accessing and modifying the fields.

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(int lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Shipments getShipment() {
        return shipment;
    }

    public void setShipment(Shipments shipment) {
        this.shipment = shipment;
    }
}

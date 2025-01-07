package com.cg.ims.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipments") // Maps this entity to the "shipments" table in the database
public class Shipments {

    // Unique identifier for the shipment (primary key)
    @Id
    @Column(name = "shipment_id") // Maps this field to the "shipment_id" column in the database
    private int shipmentId;

    // Many-to-one relationship with the "Stores" entity
    // This means each shipment is associated with one store
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false) // Maps to "store_id" column in the shipments table
    @JsonIgnore
    private Stores store;

    // Many-to-one relationship with the "Customers" entity
    // This means each shipment is associated with one customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false) // Maps to "customer_id" column in the shipments table
    @JsonIgnore
    private Customers customer;

    // One-to-many relationship with the "OrderItems" entity
    // This means each shipment can contain multiple order items
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
    @JsonIgnore
    // Cascade operations (persist, remove, etc.) and orphan removal
    private List<OrderItems> orderItems;

    // Delivery address for the shipment
    @Column(name = "delivery_address") // Maps to the "delivery_address" column in the database
    private String deliveryAddress;

    // Status of the shipment (e.g., "Shipped", "Delivered", etc.)
    @Column(name = "shipment_status") // Maps to the "shipment_status" column in the database
    private String shipmentStatus;

    // Getter and setter methods for each field

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}



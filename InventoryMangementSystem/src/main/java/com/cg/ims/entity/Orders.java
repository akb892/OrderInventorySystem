package com.cg.ims.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing an Order in the system.
 * Maps to the "orders" table in the database.
 */
@Entity
@Table(name = "orders")
public class Orders {

    /**
     * Unique identifier for each order.
     */
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    /**
     * Timestamp for when the order was placed.
     */
    @Column(name = "order_tms")
    private LocalDateTime orderTms;

    /**
     * Customer associated with the order.
     * This establishes a many-to-one relationship with the Customers entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnore
    private Customers customer;

    /**
     * Status of the order (e.g., Pending, Completed, Cancelled).
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * Store associated with the order.
     * This establishes a many-to-one relationship with the Stores entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @JsonIgnore
    private Stores store;

    /**
     * List of items in the order.
     * This establishes a one-to-many relationship with the OrderItems entity.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItems> oi;

    /**
     * Default constructor.
     * Used for creating an empty instance of Orders.
     */
    public Orders() {
        // Default constructor
    }

    /**
     * Parameterized constructor to initialize all fields of the Orders class.
     *
     * @param orderID     Unique identifier for the order.
     * @param orderTms    Timestamp of the order.
     * @param customer    Customer associated with the order.
     * @param orderStatus Status of the order.
     * @param store       Store associated with the order.
     * @param oi          List of order items in the order.
     */
    public Orders(int orderID, LocalDateTime orderTms, Customers customer, String orderStatus, Stores store,
                  List<OrderItems> oi) {
        super();
        this.orderID = orderID;
        this.orderTms = orderTms;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.store = store;
        this.oi = oi;
    }

    // Getter and Setter methods for accessing and modifying the fields.

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getOrderTms() {
        return orderTms;
    }

    public void setOrderTms(LocalDateTime orderTms) {
        this.orderTms = orderTms;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

    public List<OrderItems> getOi() {
        return oi;
    }

    public void setOi(List<OrderItems> oi) {
        this.oi = oi;
    }
}

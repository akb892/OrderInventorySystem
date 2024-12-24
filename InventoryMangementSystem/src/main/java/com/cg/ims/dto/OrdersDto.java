package com.cg.ims.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.ims.entity.Customers;
import com.cg.ims.entity.OrderItems;
import com.cg.ims.entity.Stores;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) class for Orders.
 * This class is used to encapsulate the data for transferring between layers
 * while ensuring validation constraints on the fields.
 */
public class OrdersDto {

    /**
     * Unique identifier for the order.
     * Cannot be null or blank.
     */
    @NotNull(message = "Order id cannot be null")
    @NotBlank(message = "Order id cannot be blank")
    private int orderID;

    /**
     * Timestamp of the order.
     * Formatted as "dd-MM-yyyy HH:mm:ss".
     */
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull(message = "Order timestamp is required")
    private LocalDateTime orderTms;

    /**
     * Customer associated with the order.
     * Maps to the Customers entity.
     */
    private Customers customer;

    /**
     * Status of the order.
     * Must not exceed 10 characters in length.
     */
    @NotBlank(message = "Order status is required")
    @Size(max = 10, message = "Order status must not exceed 10 characters")
    private String orderStatus;

    /**
     * Store associated with the order.
     * Maps to the Stores entity.
     */
    private Stores store;

    /**
     * List of order items associated with the order.
     * Maps to the OrderItems entity.
     */
    private List<OrderItems> oi;

    /**
     * Default constructor.
     * Used for creating an empty instance of OrdersDto.
     */
    public OrdersDto() {
        // Default constructor
    }

    /**
     * Parameterized constructor for initializing all fields of the OrdersDto.
     * 
     * @param orderID    Unique identifier for the order.
     * @param orderTms   Timestamp of the order.
     * @param customer   Customer associated with the order.
     * @param orderStatus Status of the order.
     * @param store      Store associated with the order.
     * @param oi         List of order items associated with the order.
     */
    public OrdersDto(
        @NotNull(message = "Order id cannot be null") 
        @NotBlank(message = "Order id cannot be blank") int orderID,
        LocalDateTime orderTms, 
        Customers customer,
        @Size(max = 10, message = "Order status must not exceed 10 characters") String orderStatus, 
        Stores store,
        List<OrderItems> oi) {
        super();
        this.orderID = orderID;
        this.orderTms = orderTms;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.store = store;
        this.oi = oi;
    }

    // Getter and setter methods for accessing and modifying fields

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

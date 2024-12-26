package com.cg.ims.dto;

import java.util.List;

import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;

/**
 * This class represents a composite structure that associates a customer with their orders.
 * It is primarily used to encapsulate customer details along with their corresponding order list.
 */
public class CustomerOrders {
    
    // The customer associated with the orders
    private Customers customer;
    
    // A list of orders made by the customer
    private List<Orders> order;

    /**
     * Default constructor.
     * Initializes an empty `CustomerOrders` object.
     */
    public CustomerOrders() {
    }

    /**
     * Parameterized constructor to initialize the `CustomerOrders` object.
     * 
     * @param customer The customer associated with the orders.
     * @param order    A list of orders made by the customer.
     */
    public CustomerOrders(Customers customer, List<Orders> order) {
        super();
        this.customer = customer;
        this.order = order;
    }

    /**
     * Getter for customer.
     * 
     * @return The customer associated with the orders.
     */
    public Customers getCustomer() {
        return customer;
    }

    /**
     * Setter for customer.
     * 
     * @param customer The customer to associate with the orders.
     */
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    /**
     * Getter for order list.
     * 
     * @return A list of orders made by the customer.
     */
    public List<Orders> getOrder() {
        return order;
    }

    /**
     * Setter for order list.
     * 
     * @param order A list of orders to associate with the customer.
     */
    public void setOrder(List<Orders> order) {
        this.order = order;
    }
}



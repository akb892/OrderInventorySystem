package com.cg.ims.dto;

import java.util.List;

import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Shipments;

/**
 * This class represents a composite structure that associates a customer with their shipments.
 * It is used to encapsulate customer details along with their corresponding shipment list.
 */
public class CustomerShipment {
    
    // The customer associated with the shipments
    private Customers customer;
    
    // A list of shipments related to the customer
    private List<Shipments> shipment;

    /**
     * Default constructor.
     * Initializes an empty `CustomerShipment` object.
     */
    public CustomerShipment() {
    }

    /**
     * Parameterized constructor to initialize the `CustomerShipment` object.
     * 
     * @param customer The customer associated with the shipments.
     * @param shipment A list of shipments related to the customer.
     */
    public CustomerShipment(Customers customer, List<Shipments> shipment) {
        super();
        this.customer = customer;
        this.shipment = shipment;
    }

    /**
     * Getter for customer.
     * 
     * @return The customer associated with the shipments.
     */
    public Customers getCustomer() {
        return customer;
    }

    /**
     * Setter for customer.
     * 
     * @param customer The customer to associate with the shipments.
     */
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    /**
     * Getter for shipment list.
     * 
     * @return A list of shipments related to the customer.
     */
    public List<Shipments> getShipment() {
        return shipment;
    }

    /**
     * Setter for shipment list.
     * 
     * @param shipment A list of shipments to associate with the customer.
     */
    public void setShipment(List<Shipments> shipment) {
        this.shipment = shipment;
    }
}

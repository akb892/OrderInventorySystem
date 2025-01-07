package com.cg.ims.dto;

public class ShipmentStatusCountCustomer {

    // Represents the shipment status (e.g., "Shipped", "Delivered", etc.)
    private String shipmentStatus;

    // Represents the count of shipments with this particular status
    private int count;

    // Constructor to initialize shipment status and the count (count is casted to int)
    public ShipmentStatusCountCustomer(String shipmentStatus, long count) {
        super();
        this.shipmentStatus = shipmentStatus;
        this.count = (int) count;  // Cast long count to int (assuming count is small enough to fit)
    }

    // Default constructor for initialization without parameters
    public ShipmentStatusCountCustomer() {
    }

    // Getter method for shipment status
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    // Setter method for shipment status
    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    // Getter method for the count of shipments
    public int getCount() {
        return count;
    }

    // Setter method for the count of shipments
    public void setCount(int count) {
        this.count = count;
    }
}

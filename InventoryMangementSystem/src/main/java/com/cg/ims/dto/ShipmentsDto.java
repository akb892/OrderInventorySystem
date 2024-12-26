package com.cg.ims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ShipmentsDto {

    // Store ID should not be null and is a required field
    @NotNull(message = "Store ID is required")
    private Long storeId;

    // Customer ID should not be null and is a required field
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    // Delivery address is required and should not exceed 512 characters
    @NotBlank(message = "Delivery address is required")
    @Size(max = 512, message = "Delivery address must not exceed 512 characters")
    private String deliveryAddress;

    // Shipment status is required and should not exceed 100 characters
    @NotBlank(message = "Shipment status is required")
    @Size(max = 100, message = "Shipment status must not exceed 100 characters")
    private String shipmentStatus;

    // Shipment ID is required, and it must be a positive number
    @NotNull(message = "Shipment ID is required")
    @Positive(message = "Shipment ID must be a positive number")
    private int shipmentId;

    // Getter and Setter methods for Store ID
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    // Getter and Setter methods for Shipment ID
    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    // Getter and Setter methods for Customer ID
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    // Getter and Setter methods for Delivery Address
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // Getter and Setter methods for Shipment Status
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}

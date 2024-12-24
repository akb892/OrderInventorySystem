package com.cg.ims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
public class ShipmentsDto {
    @NotNull(message = "Store ID is required")
    private Long storeId;
 
    @NotNull(message = "Customer ID is required")
    private Long customerId;
 
    @NotBlank(message = "Delivery address is required")
    @Size(max = 512, message = "Delivery address must not exceed 512 characters")
    private String deliveryAddress;
 
    @NotBlank(message = "Shipment status is required")
    @Size(max = 100, message = "Shipment status must not exceed 100 characters")
    private String shipmentStatus;
 
	public Long getStoreId() {
		return storeId;
	}
 
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
 
	public Long getCustomerId() {
		return customerId;
	}
 
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
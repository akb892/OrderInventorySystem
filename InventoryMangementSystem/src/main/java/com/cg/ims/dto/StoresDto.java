package com.cg.ims.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StoresDto {

    // Represents the unique store identifier (e.g., database ID)
    private int storeId;

    // Store name is a required field and must not exceed 255 characters
    @NotBlank(message = "Store name is required")
    @Size(max = 255, message = "Store name must not exceed 255 characters")
    private String storeName;

    // Web address (optional) must not exceed 100 characters
    @Size(max = 100, message = "Web address must not exceed 100 characters")
    private String webAddress;

    // Physical address (optional) must not exceed 512 characters
    @Size(max = 512, message = "Physical address must not exceed 512 characters")
    private String physicalAddress;

    // Getter method for storeId
    public int getStoreId() {
        return storeId;
    }

    // Setter method for storeId
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    // Getter method for storeName
    public String getStoreName() {
        return storeName;
    }

    // Setter method for storeName
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    // Getter method for webAddress
    public String getWebAddress() {
        return webAddress;
    }

    // Setter method for webAddress
    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    // Getter method for physicalAddress
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    // Setter method for physicalAddress
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
}


package com.cg.ims.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Data Transfer Object (DTO) for inventory-related operations.
 * Encapsulates inventory data to be transferred between layers in the application.
 */
public class InventoryDto {

    // Unique identifier for the inventory record
    private int inventoryId;

    // ID of the store where the inventory is located
    @NotNull(message = "Store ID is required")
    private int storeId;

    // ID of the product in the inventory
    @NotNull(message = "Product ID is required")
    private int productId;

    // Quantity of the product in the inventory
    @NotNull(message = "Product inventory is required")
    @PositiveOrZero(message = "Product inventory cannot be negative")
    private Integer productInventory;

    /**
     * Getter for inventoryId.
     * 
     * @return The unique identifier for the inventory record.
     */
    public int getInventoryId() {
        return inventoryId;
    }

    /**
     * Setter for inventoryId.
     * 
     * @param inventoryId The unique identifier to set for the inventory record.
     */
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * Getter for storeId.
     * 
     * @return The ID of the store where the inventory is located.
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Setter for storeId.
     * 
     * @param storeId The ID of the store to set.
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /**
     * Getter for productId.
     * 
     * @return The ID of the product in the inventory.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Setter for productId.
     * 
     * @param productId The ID of the product to set.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Getter for productInventory.
     * 
     * @return The quantity of the product in the inventory.
     */
    public Integer getProductInventory() {
        return productInventory;
    }

}  

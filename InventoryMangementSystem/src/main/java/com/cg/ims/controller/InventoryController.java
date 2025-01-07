package com.cg.ims.controller;



import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ims.entity.Inventory;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.InventoryService;

/**
 * REST controller for handling inventory-related operations.
 * Provides endpoints to fetch inventory data, product details, and shipment status.
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * Fetches all inventory records.
     * 
     * @return A list of all inventory items.
     * @throws InternalServerErrorException if an error occurs while fetching data.
     */
    @GetMapping()
    public List<Inventory> getAllInventoryRecords() throws InternalServerErrorException {
        return inventoryService.getAllInventoryRecords();
    }

    /**
     * Fetches inventory details for a specific store.
     * 
     * @param storeId The ID of the store.
     * @return A list of inventory details mapped to key-value pairs.
     * @throws ResourceNotFoundException if no inventory is found for the given store ID.
     */
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<List<Map<String, Object>>> fetchInventoryDetails(@PathVariable int storeId) throws ResourceNotFoundException {
        List<Map<String, Object>> response = inventoryService.getInventoryDetailsByStoreId(storeId);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Fetches inventory records associated with a specific shipment.
     * 
     * @param shipmentId The ID of the shipment.
     * @return A list of inventory items linked to the shipment.
     * @throws ResourceNotFoundException if no inventory is found for the shipment ID.
     * @throws InternalServerErrorException if an error occurs while fetching data.
     */
    @GetMapping("/shipments/{shipmentId}")
    public ResponseEntity<List<Inventory>> getInventoriesByShipmentId(@PathVariable int shipmentId) 
            throws ResourceNotFoundException, InternalServerErrorException {
        List<Inventory> inventories = inventoryService.getInventoriesByShipment(shipmentId);
        return ResponseEntity.ok(inventories);
    }

    /**
     * Fetches order details for a specific order ID.
     * 
     * @param orderId The ID of the order.
     * @return A list of order details mapped to key-value pairs.
     * @throws ResourceNotFoundException if no details are found for the given order ID.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<List<Map<String, Object>>> fetchOrderDetails(@PathVariable int orderId) throws ResourceNotFoundException {
        List<Map<String, Object>> response = inventoryService.getOrderDetailsByOrderId(orderId);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Fetches the count of products categorized by their shipment status.
     * 
     * @return A list of product counts grouped by shipment status.
     * @throws InternalServerErrorException if an error occurs while fetching data.
     */
    @GetMapping("/shipments")
    public ResponseEntity<List<Map<String, Object>>> fetchProductsCountByShipmentStatus() throws InternalServerErrorException {
        List<Map<String, Object>> response = inventoryService.getProductsCountByShipmentStatus();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Fetches product details for a specific order ID.
     * 
     * @param orderId The ID of the order.
     * @return A list of product details mapped to key-value pairs.
     * @throws ResourceNotFoundException if no product details are found for the given order ID.
     * @throws InternalServerErrorException if an error occurs while fetching data.
     */
    @GetMapping("/{orderId}/product-details")
    public ResponseEntity<List<Map<String, Object>>> fetchProductDetails(@PathVariable int orderId) 
            throws ResourceNotFoundException, InternalServerErrorException {
        List<Map<String, Object>> response = inventoryService.getProductDetailsByOrderId(orderId);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Fetches inventory records for a specific product in a specific store.
     * 
     * @param storeId The ID of the store.
     * @param productId The ID of the product.
     * @return A list of inventory items.
     * @throws ResourceNotFoundException if no inventory is found for the given product and store IDs.
     */
    @GetMapping("/store/{storeId}/product/{productId}")
    public ResponseEntity<List<Inventory>> getInventory(@PathVariable int storeId, @PathVariable int productId) 
            throws ResourceNotFoundException {
        List<Inventory> inventoryList = inventoryService.getInventoryByProductAndStore(productId, storeId);
        return ResponseEntity.ok(inventoryList);
    }

    /**
     * Fetches inventories by a specific category (e.g., color).
     * 
     * @param color The category value to filter by.
     * @return A list of inventory items matching the category.
     * @throws ResourceNotFoundException if no inventory matches the given category.
     */
    @GetMapping("/category/{color}")
    public ResponseEntity<List<Inventory>> getInventoriesByCategory(@PathVariable String color) throws ResourceNotFoundException {
        List<Inventory> inventories = inventoryService.getInventoriesByCategory(color);
        return ResponseEntity.ok(inventories);
    }
}

 
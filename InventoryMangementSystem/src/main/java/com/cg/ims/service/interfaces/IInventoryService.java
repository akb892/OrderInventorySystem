package com.cg.ims.service.interfaces;

import java.util.List;
import java.util.Map;
import com.cg.ims.entity.Inventory;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.ResourceNotFoundException;

public interface IInventoryService {

    /**
     * Fetches all inventory records.
     * @return a list of all inventory records.
     * @throws InternalServerErrorException if there is an internal server error while fetching the inventory records.
     */
    public List<Inventory> getAllInventoryRecords() throws InternalServerErrorException;

    /**
     * Fetches detailed inventory records by the provided store ID.
     * @param storeId the unique ID of the store.
     * @return a list of maps where each map contains inventory details related to a store.
     * @throws ResourceNotFoundException if no inventory records are found for the provided store ID.
     */
    public List<Map<String, Object>> getInventoryDetailsByStoreId(int storeId) throws ResourceNotFoundException;

    /**
     * Fetches inventories related to a specific shipment ID.
     * @param shipmentId the unique ID of the shipment.
     * @return a list of inventory records related to the shipment.
     * @throws ResourceNotFoundException if no inventory records are found for the provided shipment ID.
     * @throws InternalServerErrorException if there is an internal server error while fetching the inventory records.
     */
    public List<Inventory> getInventoriesByShipment(int shipmentId) throws ResourceNotFoundException, InternalServerErrorException;

    /**
     * Fetches order details based on the provided order ID.
     * @param orderId the unique ID of the order.
     * @return a list of maps containing order details.
     * @throws ResourceNotFoundException if no order details are found for the provided order ID.
     */
    public List<Map<String, Object>> getOrderDetailsByOrderId(int orderId) throws ResourceNotFoundException;

    /**
     * Fetches a count of products sold by their shipment status.
     * @return a list of maps, where each map contains a shipment status and the corresponding count of products sold.
     * @throws InternalServerErrorException if there is an internal server error while fetching the product counts.
     */
    public List<Map<String, Object>> getProductsCountByShipmentStatus() throws InternalServerErrorException;

    /**
     * Fetches product details for a given order ID.
     * @param orderId the unique ID of the order.
     * @return a list of maps, each containing product details like name, price, quantity, etc., for the specified order.
     * @throws ResourceNotFoundException if no product details are found for the provided order ID.
     * @throws InternalServerErrorException if there is an internal server error while fetching product details.
     */
    public List<Map<String, Object>> getProductDetailsByOrderId(int orderId) throws ResourceNotFoundException, InternalServerErrorException;

    /**
     * Fetches inventories for a specific category (e.g., color).
     * @param color the color category of products to filter by.
     * @return a list of inventory records matching the specified category.
     * @throws ResourceNotFoundException if no inventory records are found for the provided category.
     */
    public List<Inventory> getInventoriesByCategory(String color) throws ResourceNotFoundException;

    /**
     * Fetches inventory records for a specific product and store combination.
     * @param productId the unique ID of the product.
     * @param storeId the unique ID of the store.
     * @return a list of inventory records for the specified product and store.
     * @throws ResourceNotFoundException if no inventory records are found for the provided product and store.
     */
    public List<Inventory> getInventoryByProductAndStore(int productId, int storeId) throws ResourceNotFoundException;
}


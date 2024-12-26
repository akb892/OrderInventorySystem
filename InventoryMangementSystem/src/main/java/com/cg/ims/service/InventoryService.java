package com.cg.ims.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ims.dao.IInventoryRepo;
import com.cg.ims.dto.InventoryDto;
import com.cg.ims.dto.OrdersDto;
import com.cg.ims.dto.ProductDto;
import com.cg.ims.dto.ShipmentsDto;
import com.cg.ims.dto.StoresDto;
import com.cg.ims.entity.Inventory;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IInventoryService;

import jakarta.validation.Valid;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private IInventoryRepo inventoryRepository;

    // Fetches all inventory records from the database
    @Override
    public List<Inventory> getAllInventoryRecords() throws InternalServerErrorException {
        List<Inventory> results = inventoryRepository.findAll();
        if (!results.isEmpty()) {
            return results; // Return records if found
        } else {
            throw new InternalServerErrorException("An internal server error occurred while fetching all inventory records.");
        }
    }

    // Fetches inventory details by store ID
    @Override
    public List<Map<String, Object>> getInventoryDetailsByStoreId(int storeId) throws ResourceNotFoundException {
        StoresDto storesDto = new StoresDto();
        storesDto.setStoreId(storeId);

        // Validate store ID
        if (storesDto.getStoreId() <= 0) {
            throw new ResourceNotFoundException("Invalid store ID provided.");
        }

        // Fetch inventory details by store ID
        List<Object[]> results = inventoryRepository.fetchInventoryDetailsByStoreId(storeId);

        if (!results.isEmpty()) {
            // Process the results into a list of maps
            return results.stream()
                    .map(record -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("inventoryId", record[0]);
                        map.put("storeName", record[1]);
                        map.put("productName", record[2]);
                        map.put("unitPrice", record[3]);
                        map.put("orderStatus", record[4]);
                        return map;
                    })
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("Inventory records matching the specified store ID not found.");
        }
    }

    // Fetches inventory records based on shipment ID
    @Override
    public List<Inventory> getInventoriesByShipment(int shipmentId) throws ResourceNotFoundException, InternalServerErrorException {
        ShipmentsDto shipmentsDto = new ShipmentsDto();
        shipmentsDto.setShipmentId(shipmentId);

        // Validate shipment ID
        if (shipmentsDto.getShipmentId() <= 0) {
            throw new ResourceNotFoundException("Invalid shipment ID provided.");
        }

        // Fetch inventory records by shipment ID
        List<Inventory> results = inventoryRepository.findInventoriesByShipmentId(shipmentId);

        if (!results.isEmpty()) {
            return results; // Return results if found
        } else {
            throw new InternalServerErrorException("An internal server error occurred while fetching inventory records matching shipments.");
        }
    }

    // Fetches order details based on order ID
    @Override
    public List<Map<String, Object>> getOrderDetailsByOrderId(int orderId) throws ResourceNotFoundException {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setOrderID(orderId);

        // Validate order ID
        if (ordersDto.getOrderID() <= 0) {
            throw new ResourceNotFoundException("Invalid order ID provided.");
        }

        List<Object[]> results = null;
        results = inventoryRepository.fetchOrderDetailsByOrderId(orderId);
        if (!results.isEmpty()) {
           
            // Process the results into a list of maps
            return results.stream().map(record -> {
                Map<String, Object> map = new HashMap<>();
                map.put("storeName", record[0]);
                map.put("productName", record[1]);
                map.put("customerName", record[2]);
                return map;
            }).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("Store, product, and customer data for the specified order ID not found.");
        }
    }

    // Fetches the count of products sold by shipment status
    @Override
    public List<Map<String, Object>> getProductsCountByShipmentStatus() throws InternalServerErrorException {
        List<Object[]> results = null;
        results = inventoryRepository.countProductsSoldByShipmentStatus();

        if (!results.isEmpty()) {
           
            // Process the results into a list of maps
            return results.stream().map(record -> {
                Map<String, Object> map = new HashMap<>();
                map.put("shipmentStatus", record[0]);
                map.put("totalProductsSold", record[1]);
                return map;
            }).collect(Collectors.toList());
        } else {
            throw new InternalServerErrorException("An internal server error occurred while fetching the count of shipment status and sold products.");
        }
    }

    // Fetches product details based on order ID
    @Override
    public List<Map<String, Object>> getProductDetailsByOrderId(int orderId) throws ResourceNotFoundException, InternalServerErrorException {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setOrderID(orderId);

        // Validate order ID
        if (ordersDto.getOrderID() <= 0) {
            throw new ResourceNotFoundException("Invalid order ID provided.");
        }

        List<Object[]> results = null;
        results = inventoryRepository.fetchProductDetailsByOrderId(orderId);
        if (!results.isEmpty()) {
            
            // Process the results into a list of maps
            return results.stream().map(record -> {
                Map<String, Object> map = new HashMap<>();
                map.put("productName", record[0]);
                map.put("unitPrice", record[1]);
                map.put("quantity", record[2]);
                map.put("totalAmount", record[3]);
                map.put("storeName", record[4]);
                map.put("shipmentStatus", record[5]);
                return map;
            }).collect(Collectors.toList());
        } else {
            throw new InternalServerErrorException("List of products in the specified order ID not found with store details, shipment status, and total amount.");
        }
    }

    // Fetches inventory records based on product ID and store ID
    @Override
    public List<Inventory> getInventoryByProductAndStore(int productId, int storeId) throws ResourceNotFoundException {
        ProductDto productsDto = new ProductDto();
        StoresDto storesDto = new StoresDto();
        storesDto.setStoreId(storeId);
        productsDto.setProductId(productId);

        // Validate product ID and store ID
        if (storesDto.getStoreId() <= 0 || productsDto.getProductId() <= 0) {
            throw new ResourceNotFoundException("Invalid store ID or product ID provided.");
        }

        List<Inventory> results = inventoryRepository.findInventoryByProductIdAndStoreId(productId, storeId);

        if (!results.isEmpty()) {
            return results; // Return results if found
        } else {
            throw new ResourceNotFoundException("Inventory records for the specified product and store not found");
        }
    }

    // Fetches inventory records by product category (color)
    @Override
    public List<Inventory> getInventoriesByCategory(String color) throws ResourceNotFoundException {
        ProductDto productsDto = new ProductDto();
        productsDto.setColour(color);

        // Validate category (color)
        if (productsDto.getColour() == null) {
            throw new ResourceNotFoundException("Invalid colour provided.");
        }

        List<Inventory> results = inventoryRepository.findInventoriesByCategory(color);

        if (!results.isEmpty()) {
            return results; // Return results if found
        } else {
            throw new ResourceNotFoundException("Inventory records for the specified category not found.");
        }
    }
}



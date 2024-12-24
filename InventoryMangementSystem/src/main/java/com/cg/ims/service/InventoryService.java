package com.cg.ims.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.ims.dao.IInventoryRepo;
import com.cg.ims.entity.Inventory;
import com.cg.ims.service.interfaces.IInventoryService;
 
@Service
public class InventoryService implements IInventoryService{
	@Autowired
	private IInventoryRepo inventoryRepository;
 
	@Override
	public List<Inventory> getAllInventoryRecords() {
		return inventoryRepository.findAll();
	}
 
	@Override
	public List<Map<String, Object>> getInventoryDetailsByStoreId(int storeId) {
		List<Object[]> results=inventoryRepository.fetchInventoryDetailsByStoreId(storeId);
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
	}
   @Override
    public List<Inventory> getInventoriesByShipment(int shipmentId) {
        return inventoryRepository.findInventoriesByShipmentId(shipmentId);
    }
   @Override
    public List<Map<String, Object>> getOrderDetailsByOrderId(int orderId) {
        List<Object[]> results = inventoryRepository.fetchOrderDetailsByOrderId(orderId);
 
        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("storeName", record[0]);
            map.put("productName", record[1]);
            map.put("customerName", record[2]);
            return map;
        }).collect(Collectors.toList());
    }
 
	@Override
	 public List<Map<String, Object>> getProductsCountByShipmentStatus() {
        List<Object[]> results = inventoryRepository.countProductsSoldByShipmentStatus();
 
        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("shipmentStatus", record[0]);
            map.put("totalProductsSold", record[1]);
            return map;
        }).collect(Collectors.toList());
    }
	@Override
	 public List<Map<String, Object>> getProductDetailsByOrderId(int orderId) {
        List<Object[]> results = inventoryRepository.fetchProductDetailsByOrderId(orderId);
 
        // Transform the raw data into a list of maps for easier handling
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
    }
	@Override
	public List<Inventory> getInventoryByProductAndStore(int productId, int storeId) {
        return inventoryRepository.findInventoryByProductIdAndStoreId(productId, storeId);
    }
 
	@Override
	 public List<Inventory> getInventoriesByCategory(String color) {
	        return inventoryRepository.findInventoriesByCategory(color);
	    }
}


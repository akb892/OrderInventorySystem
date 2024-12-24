package com.cg.ims.service.interfaces;

import java.util.List;
import java.util.Map;
 
import com.cg.ims.entity.Inventory;
 
public interface IInventoryService {
   public List<Inventory> getAllInventoryRecords();
   public List<Map<String, Object>> getInventoryDetailsByStoreId(int storeId);
   public List<Inventory> getInventoriesByShipment(int shipmentId);
   public List<Map<String, Object>> getOrderDetailsByOrderId(int orderId);
   public List<Map<String, Object>> getProductsCountByShipmentStatus();
   public List<Map<String, Object>> getProductDetailsByOrderId(int orderId);
   public List<Inventory> getInventoriesByCategory(String color);
   public List<Inventory> getInventoryByProductAndStore(int productId, int storeId) ;
}


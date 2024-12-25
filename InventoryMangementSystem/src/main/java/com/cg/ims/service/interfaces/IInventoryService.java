package com.cg.ims.service.interfaces;

import java.util.List;
import java.util.Map;
import com.cg.ims.entity.Inventory;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
public interface IInventoryService {
   public List<Inventory> getAllInventoryRecords() throws InternalServerErrorException;
   public List<Map<String, Object>> getInventoryDetailsByStoreId(int storeId) throws ResourceNotFoundException;
   public List<Inventory> getInventoriesByShipment(int shipmentId) throws ResourceNotFoundException,InternalServerErrorException;
   public List<Map<String, Object>> getOrderDetailsByOrderId(int orderId) throws ResourceNotFoundException;
   public List<Map<String, Object>> getProductsCountByShipmentStatus() throws InternalServerErrorException;
   public List<Map<String, Object>> getProductDetailsByOrderId(int orderId) throws ResourceNotFoundException, InternalServerErrorException;
   public List<Inventory> getInventoriesByCategory(String color) throws ResourceNotFoundException;
   public List<Inventory> getInventoryByProductAndStore(int productId, int storeId) throws ResourceNotFoundException ;
}

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
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IInventoryService;
 
import jakarta.validation.Valid;
@Service
public class InventoryService implements IInventoryService{
	@Autowired
	private IInventoryRepo inventoryRepository;
	@Override
	public List<Inventory> getAllInventoryRecords() throws InternalServerErrorException {
		List<Inventory> results= inventoryRepository.findAll();
		if(!results.isEmpty()) {
			return results;
		}else {
			throw new InternalServerErrorException("An internal server error occurred while fetching all inventory records.");
		}
	}
	@Override
	public List<Map<String, Object>> getInventoryDetailsByStoreId(int storeId) throws ResourceNotFoundException {
	    StoresDto storesDto = new StoresDto();
	    storesDto.setStoreId(storeId);
 
	    if (storesDto.getStoreId() <= 0) {
	        throw new ResourceNotFoundException("Invalid store ID provided.");
	    }
 
	    List<Object[]> results = inventoryRepository.fetchInventoryDetailsByStoreId(storeId);
 
	    if (!results.isEmpty()) {
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
	    }else {
	    	throw new ResourceNotFoundException("Inventory records matching the specified store ID not found.");
	    }
	}
 
 
   @Override
    public List<Inventory> getInventoriesByShipment(int shipmentId) throws ResourceNotFoundException, InternalServerErrorException {
	   ShipmentsDto shipmentsDto=new ShipmentsDto();
	   shipmentsDto.setShipmentId(shipmentId);
	   if(shipmentsDto.getShipmentId()<=0) {
		   throw new ResourceNotFoundException("Invalid shipment ID provided.");
	   }
	   List<Inventory> results=inventoryRepository.findInventoriesByShipmentId(shipmentId);
	   if(!results.isEmpty()) {
		   return results;
	   }else {
		   throw new InternalServerErrorException("An internal server error occurred while fetching inventory records matching shipments.."); 
		   }
	   }
   @Override
    public List<Map<String, Object>> getOrderDetailsByOrderId(int orderId) throws ResourceNotFoundException {
	   OrdersDto ordersDto=new OrdersDto();
	   ordersDto.setOrderID(orderId);
	   if(ordersDto.getOrderID()<=0) {
		   throw new ResourceNotFoundException("Invalid order ID provided.");
	   }
	   List<Object[]> results =null;
	   if(!results.isEmpty()) {
		   results = inventoryRepository.fetchOrderDetailsByOrderId(orderId);
	        return results.stream().map(record -> {
	            Map<String, Object> map = new HashMap<>();
	            map.put("storeName", record[0]);
	            map.put("productName", record[1]);
	            map.put("customerName", record[2]);
	            return map;
	        }).collect(Collectors.toList());
	   }else {
               throw new ResourceNotFoundException("Store, product, and customer data for the specified order ID not found.");
	   }    
    }
	@Override
	 public List<Map<String, Object>> getProductsCountByShipmentStatus() throws InternalServerErrorException {
		List<Object[]> results=null;
		if(!results.isEmpty()) {
			  results = inventoryRepository.countProductsSoldByShipmentStatus();
		        return results.stream().map(record -> {
		            Map<String, Object> map = new HashMap<>();
		            map.put("shipmentStatus", record[0]);
		            map.put("totalProductsSold", record[1]);
		            return map;
		        }).collect(Collectors.toList());
		}else {
			throw new InternalServerErrorException("An internal server error occurred while fetching the count of shipment status and sold products.");
		}
    }
	@Override
	 public List<Map<String, Object>> getProductDetailsByOrderId(int orderId) throws ResourceNotFoundException, InternalServerErrorException {
		OrdersDto ordersDto=new OrdersDto();
		 ordersDto.setOrderID(orderId);
		if(ordersDto.getOrderID()<=0) {
			 throw new ResourceNotFoundException("Invalid order ID provided.");
		}
		List<Object[]> results=null;
		if(!results.isEmpty()) {
			 results = inventoryRepository.fetchProductDetailsByOrderId(orderId);
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
		}else {
			throw new InternalServerErrorException("List of products in the specified order ID not found with store details, shipment status, and total amount.");
		}
    }
	@Override
	public List<Inventory> getInventoryByProductAndStore(int productId, int storeId) throws ResourceNotFoundException {
		ProductDto productsDto= new ProductDto();
		StoresDto storesDto = new StoresDto();
		 storesDto.setStoreId(storeId);
		 productsDto.setProductId(productId);
		 if(storesDto.getStoreId() <= 0 || productsDto.getProductId() <= 0) {
			 throw new ResourceNotFoundException("Invalid store ID or product ID provided.");
		 }
		List<Inventory> results= inventoryRepository.findInventoryByProductIdAndStoreId(productId, storeId);
		if(!results.isEmpty()) {
			return results;
		}else {
			throw new ResourceNotFoundException("Inventory records for the specified product and store not found");
		}
    }
	@Override
	 public List<Inventory> getInventoriesByCategory(String color) throws ResourceNotFoundException {
		ProductDto productsDto=new ProductDto();
		productsDto.setColour(color);
		if(productsDto.getColour()==null) {
			 throw new ResourceNotFoundException("Invalid colour provided.");
		}
		List<Inventory> results = inventoryRepository.findInventoriesByCategory(color);
		if(!results.isEmpty()) {
			return results;
		}else {
			throw new ResourceNotFoundException("Inventory records for the specified category not found.");
		}
}
}


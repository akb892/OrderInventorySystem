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
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.InventoryService;
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
	@Autowired
    private InventoryService inventoryService;
	  @GetMapping()
	    public List<Inventory> getAllInventoryRecords() throws InternalServerErrorException {
	        return inventoryService.getAllInventoryRecords();  
	   }
	@GetMapping("/stores/{storeId}")
	public ResponseEntity<List<Map<String,Object>>> fetchInventoryDetails(@PathVariable int storeId) throws ResourceNotFoundException{
		List<Map<String,Object>> response = inventoryService.getInventoryDetailsByStoreId(storeId);
		if(response.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(response);
	}
	 @GetMapping("/shipments/{shipmentId}")
	    public ResponseEntity<List<Inventory>> getInventoriesByShipmentId(@PathVariable int shipmentId) throws ResourceNotFoundException, InternalServerErrorException {
	        List<Inventory> inventories = inventoryService.getInventoriesByShipment(shipmentId);
	        return ResponseEntity.ok(inventories);
	    }

	 @GetMapping("/{orderId}")
	    public ResponseEntity<List<Map<String, Object>>> fetchOrderDetails(@PathVariable int orderId) throws ResourceNotFoundException {
	        List<Map<String, Object>> response = inventoryService.getOrderDetailsByOrderId(orderId);
	        if (response.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(response);
	    }
	 @GetMapping("/shipments")
	    public ResponseEntity<List<Map<String, Object>>> fetchProductsCountByShipmentStatus() throws InternalServerErrorException {
	        List<Map<String, Object>> response = inventoryService.getProductsCountByShipmentStatus();
	        if (response.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(response);
	    }
	  @GetMapping("/{orderId}/product-details")
	    public ResponseEntity<List<Map<String, Object>>> fetchProductDetails(@PathVariable int orderId) throws ResourceNotFoundException, InternalServerErrorException {
	        List<Map<String, Object>> response = inventoryService.getProductDetailsByOrderId(orderId);
	        if (response.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(response);
	    }
	  @GetMapping("/store/{storeId}/product/{productId}")
	    public ResponseEntity<List<Inventory>> getInventory(
	            @PathVariable int storeId,
	            @PathVariable int productId) throws ResourceNotFoundException {
	        List<Inventory> inventoryList = inventoryService.getInventoryByProductAndStore(productId, storeId);
	        return ResponseEntity.ok(inventoryList);
	    }
	  @GetMapping("/category/{color}")
	    public ResponseEntity<List<Inventory>> getInventoriesByCategory(@PathVariable String color) throws ResourceNotFoundException {
	        List<Inventory> inventories = inventoryService.getInventoriesByCategory(color);
	        return ResponseEntity.ok(inventories);
	    }
}
 
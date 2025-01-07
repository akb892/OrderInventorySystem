package com.cg.ims;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.cg.ims.dao.IInventoryRepo;
import com.cg.ims.entity.Inventory;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.InventoryService;
 
@SpringBootTest
public class InventoryServiceTest {
 
    @InjectMocks
    private InventoryService inventoryService;
 
    @Mock
    private IInventoryRepo inventoryRepository;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void testGetAllInventoryRecords_Success() throws InternalServerErrorException {
        List<Inventory> mockInventories = new ArrayList<>();
        mockInventories.add(new Inventory());
 
        when(inventoryRepository.findAll()).thenReturn(mockInventories);
 
        List<Inventory> result = inventoryService.getAllInventoryRecords();
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryRepository, times(1)).findAll();
    }
 
    @Test
    public void testGetAllInventoryRecords_NoRecordsFound() {
        when(inventoryRepository.findAll()).thenReturn(Collections.emptyList());
 
        assertThrows(InternalServerErrorException.class, () -> inventoryService.getAllInventoryRecords());
        verify(inventoryRepository, times(1)).findAll();
    }
 
    @Test
    public void testGetInventoryDetailsByStoreId_Success() throws ResourceNotFoundException {
        int storeId = 1;
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{1, "Store A", "Product A", 100.0, "Delivered"});
 
        when(inventoryRepository.fetchInventoryDetailsByStoreId(storeId)).thenReturn(mockResults);
 
        List<Map<String, Object>> result = inventoryService.getInventoryDetailsByStoreId(storeId);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Store A", result.get(0).get("storeName"));
        verify(inventoryRepository, times(1)).fetchInventoryDetailsByStoreId(storeId);
    }
 
    @Test
    public void testGetInventoryDetailsByStoreId_InvalidStoreId() {
        int storeId = -1;
 
        assertThrows(ResourceNotFoundException.class, () -> inventoryService.getInventoryDetailsByStoreId(storeId));
        verify(inventoryRepository, never()).fetchInventoryDetailsByStoreId(anyInt());
    }
 
    @Test
    public void testGetInventoryDetailsByStoreId_NoRecordsFound() {
        int storeId = 1;
        when(inventoryRepository.fetchInventoryDetailsByStoreId(storeId)).thenReturn(Collections.emptyList());
 
        assertThrows(ResourceNotFoundException.class, () -> inventoryService.getInventoryDetailsByStoreId(storeId));
        verify(inventoryRepository, times(1)).fetchInventoryDetailsByStoreId(storeId);
    }
 
    @Test
    public void testGetInventoriesByShipment_Success() throws ResourceNotFoundException, InternalServerErrorException {
        int shipmentId = 1;
        List<Inventory> mockInventories = new ArrayList<>();
        mockInventories.add(new Inventory());
 
        when(inventoryRepository.findInventoriesByShipmentId(shipmentId)).thenReturn(mockInventories);
 
        List<Inventory> result = inventoryService.getInventoriesByShipment(shipmentId);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryRepository, times(1)).findInventoriesByShipmentId(shipmentId);
    }
 
    @Test
    public void testGetInventoriesByShipment_InvalidShipmentId() {
        int shipmentId = -1;
 
        assertThrows(ResourceNotFoundException.class, () -> inventoryService.getInventoriesByShipment(shipmentId));
        verify(inventoryRepository, never()).findInventoriesByShipmentId(anyInt());
    }
 
    @Test
    public void testGetInventoriesByShipment_NoRecordsFound() {
        int shipmentId = 1;
        when(inventoryRepository.findInventoriesByShipmentId(shipmentId)).thenReturn(Collections.emptyList());
 
        assertThrows(InternalServerErrorException.class, () -> inventoryService.getInventoriesByShipment(shipmentId));
        verify(inventoryRepository, times(1)).findInventoriesByShipmentId(shipmentId);
    }
 
    @Test
    public void testGetInventoryByProductAndStore_Success() throws ResourceNotFoundException {
        int productId = 1;
        int storeId = 1;
        List<Inventory> mockInventories = new ArrayList<>();
        mockInventories.add(new Inventory());
 
        when(inventoryRepository.findInventoryByProductIdAndStoreId(productId, storeId)).thenReturn(mockInventories);
 
        List<Inventory> result = inventoryService.getInventoryByProductAndStore(productId, storeId);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryRepository, times(1)).findInventoryByProductIdAndStoreId(productId, storeId);
    }
 
    @Test
    public void testGetInventoryByProductAndStore_InvalidIds() {
        int productId = -1;
        int storeId = -1;
 
        assertThrows(ResourceNotFoundException.class, () -> inventoryService.getInventoryByProductAndStore(productId, storeId));
        verify(inventoryRepository, never()).findInventoryByProductIdAndStoreId(anyInt(), anyInt());
    }
 
    @Test
    public void testGetInventoryByProductAndStore_NoRecordsFound() {
        int productId = 1;
        int storeId = 1;
        when(inventoryRepository.findInventoryByProductIdAndStoreId(productId, storeId)).thenReturn(Collections.emptyList());
 
        assertThrows(ResourceNotFoundException.class, () -> inventoryService.getInventoryByProductAndStore(productId, storeId));
        verify(inventoryRepository, times(1)).findInventoryByProductIdAndStoreId(productId, storeId);
    }
}

package com.cg.ims.dao;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.cg.ims.entity.Inventory;
 
@Repository
public interface IInventoryRepo extends JpaRepository<Inventory, Integer>{
// Fetch productdata, storedata, orderstatus matching storied
	@Query("SELECT DISTINCT i.inventoryId, s.storeName, p.productName, p.unitPrice, o.orderStatus " +
		       "FROM Inventory i " +
		       "JOIN i.product p " +
		       "JOIN i.store s " +
		       "JOIN s.orders o " +
		       "WHERE s.storeId = :storeId")
		List<Object[]> fetchInventoryDetailsByStoreId(@Param("storeId") int storeId);
  //Fetch inventories and matching shipments
	@Query("SELECT i FROM Inventory i JOIN i.store s JOIN Shipments sh ON s.storeId = sh.store.storeId WHERE sh.shipmentId = :shipmentId")
		List<Inventory> findInventoriesByShipmentId(@Param("shipmentId") int shipmentId);
 
//Fetch store, product and customer data matching order id
	    @Query("""
		    SELECT 
		        s.storeName, 
		        p.productName, 
		        c.fullName 
		    FROM Orders o
		    JOIN o.store s
		    JOIN o.customer c
		    JOIN o.oi oi1
		    JOIN oi1.product p
		    WHERE o.orderID = :orderId
		""")
		List<Object[]> fetchOrderDetailsByOrderId(@Param("orderId") int orderId);
 
 
//  Count shipment status wise count of total products sold
	    @Query("""
	    	    SELECT sh.shipmentStatus, COUNT(oi1.product.productId)
	    	    FROM Shipments sh
	    	    JOIN sh.store s
	    	    JOIN s.orders o
	    	    JOIN o.oi oi1
	    	    GROUP BY sh.shipmentStatus
	    	    """)
	    	List<Object[]> countProductsSoldByShipmentStatus();
 
	  
//Fetch list of Products in a order matching ordered with store details, shipment status and total amount
	    	@Query("""
	    		    SELECT 
	    		        p.productName,
	    		        p.unitPrice,
	    		        SUM(oi1.quantity) AS quantity,
	    		        SUM(oi1.unitPrice * oi1.quantity) AS totalAmount,
	    		        s.storeName,
	    		        sh.shipmentStatus
	    		    FROM Orders o
	    		    JOIN o.store s
	    		    JOIN o.oi oi1
	    		    JOIN oi1.product p
	    		    LEFT JOIN s.shipments sh
	    		    WHERE o.orderID = :orderId
	    		    GROUP BY p.productName, p.unitPrice, s.storeName, sh.shipmentStatus
	    		""")
	    		List<Object[]> fetchProductDetailsByOrderId(@Param("orderId") int orderId);
//Retrieve inventory records for a specific product and store
	    		@Query("SELECT i FROM Inventory i WHERE i.product.productId = :productId AND i.store.storeId = :storeId")
	    		List<Inventory> findInventoryByProductIdAndStoreId(@Param("productId") int productId, @Param("storeId") int storeId);
 
	    		
//Retrieve inventory records for products in a specific category
	    		@Query("SELECT i FROM Inventory i JOIN i.product p WHERE p.color = :color")
	    		List<Inventory> findInventoriesByCategory(@Param("color") String color);
 
 
	 }


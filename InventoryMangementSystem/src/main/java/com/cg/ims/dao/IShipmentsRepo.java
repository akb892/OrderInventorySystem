package com.cg.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Shipments;

@Repository
public interface IShipmentsRepo extends JpaRepository<Shipments, Integer>{

	@Query("SELECT s FROM Shipments s WHERE s.customer.id = :customerId")
    List<Shipments> findShipmentsByCustomerId(Integer customerId);
	
//	@Query("SELECT s FROM Shipments s WHERE s.status = 'PENDING'")
//    List<Shipments> findPendingShipments();
//	
//	 @Query("SELECT s FROM Shipments s WHERE s.status = 'OVERDUE'")
//	    List<Shipments> findOverdueShipments();
	
	
	
}

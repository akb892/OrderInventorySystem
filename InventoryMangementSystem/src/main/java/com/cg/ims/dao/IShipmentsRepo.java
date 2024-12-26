package com.cg.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Shipments;

/**
 * Repository interface for performing CRUD operations and custom queries
 * on the Shipments entity.
 */
@Repository
public interface IShipmentsRepo extends JpaRepository<Shipments, Integer> {

    /**
     * Finds all shipments associated with a specific customer.
     * 
     * @param customerId The ID of the customer for whom shipments are to be retrieved.
     * @return A list of Shipments linked to the specified customer.
     */
    @Query("SELECT s FROM Shipments s WHERE s.customer.id = :customerId")
    List<Shipments> findShipmentsByCustomerId(Integer customerId);

}

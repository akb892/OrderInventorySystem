package com.cg.ims.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ims.dto.ShipmentStatusCountCustomer;
import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
import com.cg.ims.entity.Shipments;

/**
 * Repository interface for performing CRUD operations and custom queries
 * on the Customers entity.
 */
@Repository
public interface ICustomerRepository extends JpaRepository<Customers, Integer> {

    /**
     * Finds customers by their email address.
     * 
     * @param emailAddress The email address to search for.
     * @return A list of customers with the specified email address.
     */
    List<Customers> findByEmailAddress(String emailAddress);

    /**
     * Finds customers whose names contain the specified string (case-insensitive).
     * 
     * @param name The substring to search for in customer names.
     * @return A list of customers with names containing the given substring.
     */
    @Query("SELECT c FROM Customers c WHERE c.fullName LIKE %:name%")
    List<Customers> findByNameContaining(@Param("name") String name);

    /**
     * Fetches the count of unique customers grouped by their shipment statuses.
     * 
     * @return A list of shipment status counts for distinct customers.
     */
    @Query("SELECT new com.cg.ims.dto.ShipmentStatusCountCustomer(s.shipmentStatus, COUNT(DISTINCT c.customerId)) " +
           "FROM Shipments s " +
           "JOIN s.customer c " +
           "GROUP BY s.shipmentStatus")
    List<ShipmentStatusCountCustomer> getCustomerCountByStatus();

    /**
     * Fetches all orders placed by a specific customer.
     * 
     * @param customerId The ID of the customer.
     * @return A list of orders associated with the given customer.
     */
    @Query("SELECT o FROM Orders o WHERE o.customer.customerId = :customerId")
    List<Orders> getCustomerOrders(@Param("customerId") int customerId);

    /**
     * Fetches all shipments associated with a specific customer.
     * 
     * @param customerId The ID of the customer.
     * @return A list of shipments linked to the customer.
     */
    @Query("SELECT s FROM Shipments s WHERE s.customer.customerId = :Id")
    List<Shipments> getCustomerShipments(@Param("Id") int customerId);

    /**
     * Finds customers who have pending shipments.
     * 
     * @return A list of customers with at least one pending shipment.
     */
    @Query("SELECT DISTINCT c FROM Customers c JOIN c.shipments s WHERE s.shipmentStatus = 'PENDING'")
    List<Customers> findCustomersWithPendingShipments();

    /**
     * Finds customers who have completed orders.
     * 
     * @return A list of customers with at least one completed order.
     */
    @Query("SELECT DISTINCT c FROM Customers c JOIN c.order o WHERE o.orderStatus = 'COMPLETE'")
    List<Customers> findCustomersWithCompletedOrders();

    /**
     * Finds customers who have overdue shipments.
     * 
     * @return A list of customers with at least one overdue shipment.
     */
    @Query("SELECT DISTINCT c FROM Customers c JOIN c.shipments s WHERE s.shipmentStatus = 'OVERDUE'")
    List<Customers> findCustomersWithOverdueShipments();

    /**
     * Finds customers based on the range of quantities in their orders.
     * 
     * @param min The minimum quantity.
     * @param max The maximum quantity.
     * @return A list of customers whose orders have quantities within the specified range.
     */
    @Query("SELECT c FROM Customers c JOIN c.order o JOIN o.oi oi1 WHERE oi1.quantity BETWEEN :min AND :max")
    List<Customers> findCustomersByOrderQuantityRange(@Param("min") int min, @Param("max") int max);
}

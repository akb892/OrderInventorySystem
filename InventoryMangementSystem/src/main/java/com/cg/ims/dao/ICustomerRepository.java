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
 
@Repository
public interface ICustomerRepository extends JpaRepository<Customers, Integer> {
	List<Customers> findByEmailAddress(String emailAddress);
	
	
//    Optional<Customers> findByEmailAddress(String email);
    @Query("SELECT c FROM Customers c WHERE c.fullName LIKE %:name%")
    List<Customers> findByNameContaining(@Param("name") String name);
    //Shipment status wise count of customers
    @Query("SELECT new com.cg.ims.dto.ShipmentStatusCountCustomer(s.shipmentStatus, COUNT(DISTINCT c.customerId)) " +
            "FROM Shipments s " +
            "JOIN s.customer c " +
            "GROUP BY s.shipmentStatus")
     List<ShipmentStatusCountCustomer> getCustomerCountByStatus();
    @Query("SELECT o FROM Orders o WHERE o.customer.customerId = :customerId")
    List<Orders> getCustomerOrders(@Param("customerId") int customerId);
    @Query("SELECT s FROM Shipments s WHERE s.customer.customerId = :Id")
    public List<Shipments> getCustomerShipments(@Param("Id") int customerId);
    @Query("SELECT DISTINCT c FROM Customers c JOIN c.shipments s WHERE s.shipmentStatus = 'PENDING'")
    List<Customers> findCustomersWithPendingShipments();
    @Query("SELECT DISTINCT c FROM Customers c JOIN c.order o WHERE o.orderStatus = 'COMPLETE'")
    List<Customers> findCustomersWithCompletedOrders();
    @Query("SELECT DISTINCT c FROM Customers c JOIN c.shipments s WHERE s.shipmentStatus = 'OVERDUE'")
    List<Customers> findCustomersWithOverdueShipments();
    @Query("SELECT c FROM Customers c JOIN c.order o Join o.oi oi1 where oi1.quantity BETWEEN :min AND :max")
    List<Customers> findCustomersByOrderQuantityRange(@Param("min") int min, @Param("max") int max);
}
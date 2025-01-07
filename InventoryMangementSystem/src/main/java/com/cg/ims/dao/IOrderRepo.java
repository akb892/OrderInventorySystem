package com.cg.ims.dao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Orders;

@Repository //created a repository for orders using inbuilt jpa repository
public interface IOrderRepo extends JpaRepository<Orders, Integer> {

	//writing a query for count of order by order status

    @Query("SELECT o.orderStatus, COUNT(o.orderID) FROM Orders o GROUP BY o.orderStatus")
    List<Object[]> countOrdersGroupedByStatus();
	// created an in built method of find order by status
	List<Orders> findByOrderStatus(String orderStatus);
	// writing a query for finding orders within date range
	@Query("select o from Orders o where o.orderTms between :startDate and :endDate")
	List<Orders> findOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);
	// creating a in built method for find by order id
	List<Orders> findByOrderID(int orderID);
	
	
	@Query("SELECT o FROM Orders o WHERE o.customer.id = :customerId")
    List<Orders> findOrdersByCustomerId(Integer customerId);
	
	@Query("Select o from Orders o where o.orderID = :id")
	Orders findSingleOrderById(int id);
	
}

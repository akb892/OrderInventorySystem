package com.cg.ims.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Orders;

@Repository //created a repository for orders using inbuilt jpa repository
public interface IOrderRepo extends JpaRepository<Orders, Integer> {

	//writing a query for count of order by order status
	@Query("select o.orderStatus as status, count(o) as count from Orders o group by o.orderStatus")
	List<Object[]> countOrdersByStatus();
	// created an in built method of find order by status
	List<Orders> findByOrderStatus(String orderStatus);
	// writing a query for finding orders within date range
	@Query("select o from Orders o where o.orderTms between :startDate and :endDate")
	List<Orders> findOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);
	// creating a in built method for find by order id
	List<Orders> findByOrderID(int orderID);
}

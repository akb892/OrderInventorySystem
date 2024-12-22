package com.cg.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Orders;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Integer> {

	@Query("select o.orderStatus as status, count(o) as count from Orders o group by o.orderStatus")
	List<Object[]> countOrdersByStatus();
	List<Orders> findByOrderStatus(String orderStatus);
}

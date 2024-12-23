package com.cg.ims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ims.entity.Customers;

public interface ICustomerRepository extends JpaRepository<Customers, Integer> {
	

}

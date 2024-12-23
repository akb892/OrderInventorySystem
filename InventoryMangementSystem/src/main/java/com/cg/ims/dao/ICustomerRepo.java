package com.cg.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Customers;


@Repository
public interface ICustomerRepo extends JpaRepository<Customers, Integer> {
	
	List<Customers> findByEmailAddress(String emailAddress);
	
}

package com.cg.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Stores;

@Repository
public interface IStoreRepo extends JpaRepository<Stores, Integer>{

	List<Stores> findByStoreName(String storeName);
}

package com.cg.ims.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ims.entity.Stores;

/**
 * Repository interface for performing CRUD operations and custom queries
 * on the Stores entity.
 */
@Repository
public interface IStoreRepo extends JpaRepository<Stores, Integer> {

    /**
     * Finds a list of stores by their name.
     * 
     * @param storeName The name of the store(s) to search for.
     * @return A list of Stores that match the given store name.
     */
    List<Stores> findByStoreName(String storeName);
}

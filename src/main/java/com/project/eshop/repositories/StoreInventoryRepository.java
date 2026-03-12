package com.project.eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.eshop.entity.StoreInventory;

@Repository
public interface StoreInventoryRepository extends JpaRepository<StoreInventory, Integer> {

}

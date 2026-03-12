package com.project.eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.eshop.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

}

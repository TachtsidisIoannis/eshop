package com.project.eshop.repositories;

import com.project.eshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Category, Integer> {

}

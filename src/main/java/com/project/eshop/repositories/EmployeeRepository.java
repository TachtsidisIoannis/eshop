package com.project.eshop.repositories;

import com.project.eshop.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByStoreId(Integer storeId);
}

package com.project.eshop.controller;

import com.project.eshop.entity.Employee;
import com.project.eshop.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	 @Autowired
	    private EmployeeRepository employeeRepository;

	    @GetMapping
	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
	        Optional<Employee> employee = employeeRepository.findById(id);
	        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @GetMapping("/store/{storeId}")
	    public List<Employee> getEmployeesByStore(@PathVariable Integer storeId) {
	        return employeeRepository.findByStoreId(storeId);
	    }

	    @PostMapping
	    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
	        Employee savedEmployee = employeeRepository.save(employee);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

	        if (optionalEmployee.isPresent()) {
	            Employee existingEmployee = optionalEmployee.get();
	            existingEmployee.setFirstName(employeeDetails.getFirstName());
	            existingEmployee.setLastName(employeeDetails.getLastName());
	            existingEmployee.setPosition(employeeDetails.getPosition());
	            existingEmployee.setStore(employeeDetails.getStore());
	            
	            Employee updatedEmployee = employeeRepository.save(existingEmployee);
	            return ResponseEntity.ok(updatedEmployee);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
	        if (employeeRepository.existsById(id)) {
	            employeeRepository.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}

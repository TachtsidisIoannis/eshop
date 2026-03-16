package com.project.eshop.service;

import com.project.eshop.dto.EmployeeDTO;
import com.project.eshop.entity.Employee;
import com.project.eshop.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(this::convertToDTO).orElse(null);
    }

    public List<EmployeeDTO> getEmployeesByStore(Integer storeId) {
        return employeeRepository.findByStoreId(storeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setPosition(employee.getPosition());

        if (employee.getStore() != null) {
            dto.setStoreId(employee.getStore().getId());
            dto.setStoreCity(employee.getStore().getCity());
        }
        return dto;
    }
}

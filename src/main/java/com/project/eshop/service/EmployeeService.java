package com.project.eshop.service;

import com.project.eshop.dto.EmployeeDTO;
import com.project.eshop.entity.Employee;
import com.project.eshop.repositories.EmployeeRepository;
import com.project.eshop.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
        return convertToDTO(employee);
    }

    public List<EmployeeDTO> getEmployeesByStore(Integer storeId) {
        return employeeRepository.findByStoreId(storeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPosition(dto.getPosition());

        employee.setStore(storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store ID not found!")));

        return convertToDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        existingEmployee.setFirstName(dto.getFirstName());
        existingEmployee.setLastName(dto.getLastName());
        existingEmployee.setPosition(dto.getPosition());

        existingEmployee.setStore(storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store ID not found!")));

        return convertToDTO(employeeRepository.save(existingEmployee));
    }

    public boolean deleteEmployee(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
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

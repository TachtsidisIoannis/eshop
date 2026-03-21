package com.project.eshop.service;

import com.project.eshop.dto.CustomerDTO;
import com.project.eshop.entity.Customer;
import com.project.eshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
	@Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return convertToDTO(customer.get());
        }
        return null;
    }
    
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
    	Customer customerEntity = convertToEntity(customerDTO);
    	Customer savedCustomer = customerRepository.save(customerEntity);
    	return convertToDTO(savedCustomer);
    }
    
    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
    	Optional<Customer> optionalCustomer = customerRepository.findById(id);
    	if(optionalCustomer.isPresent()) {
    		Customer existingCustomer = optionalCustomer.get();
    		existingCustomer.setFirstName(customerDTO.getFirstName());
    		existingCustomer.setLastName(customerDTO.getLastName());
    		existingCustomer.setEmail(customerDTO.getEmail());
    		existingCustomer.setPhone(customerDTO.getPhone());
    	    existingCustomer.setCity(customerDTO.getCity());
    	    existingCustomer.setAddress(customerDTO.getAddress());
    		Customer updatedCustomer = customerRepository.save(existingCustomer);
    		return convertToDTO(updatedCustomer);
    	}
    	return null;
    }
    
    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCity(customer.getCity());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    private Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        return customer;
    }
}

package com.project.eshop.service;

import com.project.eshop.dto.RegisterDTO;
import com.project.eshop.entity.Customer;
import com.project.eshop.entity.User;
import com.project.eshop.repositories.CustomerRepository;
import com.project.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
public String registerCustomer(RegisterDTO dto) {
        
        // 1. Check if passwords match
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }

        // 2. Check if username is already taken
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }

        // 3. Create the Security User account
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // ENCRYPT IT!
        user.setRole("ROLE_CUSTOMER"); // Force them to be a customer, not a manager!
        
        User savedUser = userRepository.save(user);

        // 4. Create their empty Customer profile and link it to the User account
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setUser(savedUser); 
        // Notice we leave firstName, lastName, address, city, and phone completely blank (null)!
        
        customerRepository.save(customer);

        return "Customer registered successfully!";
    }
}

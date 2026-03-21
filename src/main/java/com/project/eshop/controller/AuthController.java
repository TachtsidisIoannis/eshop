package com.project.eshop.controller;

import com.project.eshop.dto.RegisterDTO;
import com.project.eshop.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	 @Autowired
	 private AuthService authService;

	 @PostMapping("/register")
	 public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
	     String message = authService.registerCustomer(registerDTO);
	     return ResponseEntity.status(HttpStatus.CREATED).body(message);
	 }
}

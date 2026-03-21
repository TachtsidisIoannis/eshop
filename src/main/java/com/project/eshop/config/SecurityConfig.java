package com.project.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
            .authorizeHttpRequests(auth -> auth
                // 1. PUBLIC ENDPOINTS (No login required)
                .requestMatchers("/api/auth/**").permitAll() // Let people register!
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll() // Let people browse
                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll() 

                // 2. MANAGER ONLY ENDPOINTS (Strictly enforced)
                .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("MANAGER")
                
                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("MANAGER")

                // Managers control the Stores and Inventory completely
                .requestMatchers("/api/stores/**").hasRole("MANAGER")
                .requestMatchers("/api/inventory/**").hasRole("MANAGER")

                // Managers can view ALL customers or delete them
                .requestMatchers(HttpMethod.GET, "/api/customers").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("MANAGER")

                // ONLY Managers can view all employees, hire (POST), or fire (DELETE) them!
                .requestMatchers("/api/employees/**").hasRole("MANAGER")

                // 3. ANY LOGGED-IN USER (Customer, Employee, or Manager)
                // This allows them to view/update their OWN profile, or place an order.
                .anyRequest().authenticated()
            )
            // Use Basic Auth for Postman testing
            .httpBasic(basic -> {}); 

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

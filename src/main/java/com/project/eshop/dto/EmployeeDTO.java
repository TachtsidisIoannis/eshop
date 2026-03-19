package com.project.eshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Integer id;
	
	@NotBlank
    private String firstName;
    
	@NotBlank
    private String lastName;
    
	@NotBlank
    private String position;
    
	@NotNull
    private Integer storeId;
    private String storeCity; 
}

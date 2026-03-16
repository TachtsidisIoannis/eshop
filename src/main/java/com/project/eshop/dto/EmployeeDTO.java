package com.project.eshop.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer storeId;
    private String storeCity; 
}

package com.project.eshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreDTO {
	
	private Integer id;
	
	@NotBlank
    private String city;
	
	@NotBlank
    private String address;
}

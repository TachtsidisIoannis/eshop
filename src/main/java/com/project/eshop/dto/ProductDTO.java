package com.project.eshop.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
	
	private Integer id;
	
	@NotBlank
    private String name;
	
	@NotNull
	@Min(value = 0)
    private BigDecimal price;
	
	@NotNull
	@Min(value = 0)
    private Integer stock;
	
	@NotNull
    private Integer categoryId;
    
    private String categoryName;
}

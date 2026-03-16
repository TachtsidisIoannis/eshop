package com.project.eshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Integer categoryId;
    private String categoryName;
}

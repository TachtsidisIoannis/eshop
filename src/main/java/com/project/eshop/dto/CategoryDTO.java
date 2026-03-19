package com.project.eshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
	
	private Integer id;
	@NotBlank
    private String name;
}

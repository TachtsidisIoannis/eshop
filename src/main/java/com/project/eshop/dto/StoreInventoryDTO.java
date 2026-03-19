package com.project.eshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreInventoryDTO {
	
	 private Integer id;
	 
	 @NotNull
	 @Min(value = 0)
	 private Integer quantity;
	 
	 @NotNull
	 private Integer storeId;
	 
	 @NotNull
	 private String storeCity;
	 private Integer productId;
	 private String productName;
}

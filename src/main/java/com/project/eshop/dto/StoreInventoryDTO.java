package com.project.eshop.dto;

import lombok.Data;

@Data
public class StoreInventoryDTO {
	
	 private Integer id;
	 private Integer quantity;
	 private Integer storeId;
	 private String storeCity;
	 private Integer productId;
	 private String productName;
}

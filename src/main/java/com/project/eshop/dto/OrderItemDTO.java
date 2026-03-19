package com.project.eshop.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDTO {
	
	private Integer id;
	
	@NotNull
	@Min(value = 0)
    private Integer quantity;
    private BigDecimal price;
    
    @NotNull
    private Integer productId;
    private String productName;
    private Integer orderId;
}

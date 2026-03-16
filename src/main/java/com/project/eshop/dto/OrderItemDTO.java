package com.project.eshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDTO {
	private Integer id;
    private Integer quantity;
    private BigDecimal price;
    private Integer productId;
    private String productName;
    private Integer orderId;
}

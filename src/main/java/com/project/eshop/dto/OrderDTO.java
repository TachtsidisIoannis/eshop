package com.project.eshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	private Integer id;
    private LocalDate orderDate;
    private String status;
    private BigDecimal totalAmount;
    private Integer customerId;
    private String customerFullName;
    private List<OrderItemDTO> items;
}

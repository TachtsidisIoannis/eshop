package com.project.eshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
	
	private Integer id;
    private LocalDate orderDate;
    private String status;
    private BigDecimal totalAmount;
    
    @NotNull
    private Integer customerId;
    private String customerFullName;
    
    @NotEmpty
    private List<OrderItemDTO> items;
}

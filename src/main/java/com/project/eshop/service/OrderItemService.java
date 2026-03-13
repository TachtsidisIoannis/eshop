package com.project.eshop.service;

import com.project.eshop.dto.OrderItemDTO;
import com.project.eshop.entity.OrderItem;
import com.project.eshop.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
	
	@Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderItemDTO getOrderItemById(Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.map(this::convertToDTO).orElse(null);
    }

    public List<OrderItemDTO> getItemsByOrder(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderItemDTO convertToDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());

        if (item.getProduct() != null) {
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
        }

        if (item.getOrder() != null) {
            dto.setOrderId(item.getOrder().getId());
        }

        return dto;
    }
}

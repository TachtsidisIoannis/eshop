package com.project.eshop.service;

import com.project.eshop.dto.OrderItemDTO;
import com.project.eshop.entity.OrderItem;
import com.project.eshop.repositories.OrderItemRepository;
import com.project.eshop.repositories.OrderRepository;
import com.project.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
	
	@Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderItemDTO getOrderItemById(Integer id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found!"));
        return convertToDTO(item);
    }

    public List<OrderItemDTO> getItemsByOrder(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderItemDTO createOrderItem(OrderItemDTO dto) {
        OrderItem item = new OrderItem();
        item.setQuantity(dto.getQuantity());

        item.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found!")));
        
        item.setOrder(orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found!")));
        
        item.setPrice(item.getProduct().getPrice());

        return convertToDTO(orderItemRepository.save(item));
    }

    public OrderItemDTO updateOrderItem(Integer id, OrderItemDTO dto) {
        OrderItem existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found!"));

        existing.setQuantity(dto.getQuantity());
        
        existing.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found!")));
        
        existing.setPrice(existing.getProduct().getPrice());

        return convertToDTO(orderItemRepository.save(existing));
    }

    public boolean deleteOrderItem(Integer id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
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

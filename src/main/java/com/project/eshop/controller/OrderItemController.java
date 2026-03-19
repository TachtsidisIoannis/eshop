package com.project.eshop.controller;

import com.project.eshop.dto.OrderItemDTO;
import com.project.eshop.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin(origins = "*")
public class OrderItemController {
	
	@Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItemDTO> getItemsByOrder(@PathVariable Integer orderId) {
        return orderItemService.getItemsByOrder(orderId);
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@Valid @RequestBody OrderItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.createOrderItem(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Integer id, @Valid @RequestBody OrderItemDTO dto) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id) {
        if (orderItemService.deleteOrderItem(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

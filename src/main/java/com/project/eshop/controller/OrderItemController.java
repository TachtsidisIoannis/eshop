package com.project.eshop.controller;

import com.project.eshop.entity.OrderItem;
import com.project.eshop.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin(origins = "*")
public class OrderItemController {
	
	@Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // BONUS: See all items inside a specific order (e.g. /api/order-items/order/1)
    @GetMapping("/order/{orderId}")
    public List<OrderItem> getItemsByOrder(@PathVariable Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem itemDetails) {
        Optional<OrderItem> optionalItem = orderItemRepository.findById(id);

        if (optionalItem.isPresent()) {
            OrderItem existingItem = optionalItem.get();
            existingItem.setQuantity(itemDetails.getQuantity());
            existingItem.setPrice(itemDetails.getPrice());
            existingItem.setProduct(itemDetails.getProduct());
            existingItem.setOrder(itemDetails.getOrder());
            
            OrderItem updatedItem = orderItemRepository.save(existingItem);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

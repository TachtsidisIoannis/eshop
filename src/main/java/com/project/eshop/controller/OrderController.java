package com.project.eshop.controller;

import com.project.eshop.entity.Order;
import com.project.eshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // BONUS: Get all orders for a specific customer
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order orderDetails) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setOrderDate(orderDetails.getOrderDate());
            existingOrder.setStatus(orderDetails.getStatus());
            existingOrder.setTotalAmount(orderDetails.getTotalAmount());
            existingOrder.setCustomer(orderDetails.getCustomer());
            
            Order updatedOrder = orderRepository.save(existingOrder);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

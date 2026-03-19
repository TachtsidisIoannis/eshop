package com.project.eshop.service;

import com.project.eshop.dto.OrderDTO;
import com.project.eshop.dto.OrderItemDTO;
import com.project.eshop.entity.Customer;
import com.project.eshop.entity.Order;
import com.project.eshop.entity.OrderItem;
import com.project.eshop.entity.Product;
import com.project.eshop.repositories.CustomerRepository;
import com.project.eshop.repositories.OrderItemRepository;
import com.project.eshop.repositories.OrderRepository;
import com.project.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
	
	@Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        return convertToDTO(order);
    }

    public List<OrderDTO> getOrdersByCustomer(Integer customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setStatus("Pending");
        order.setTotalAmount(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order);

        BigDecimal finalTotal = BigDecimal.ZERO;

        for (OrderItemDTO itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product ID " + itemDto.getProductId() + " not found!"));

            if (product.getStock() < itemDto.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getName() + "!");
            }

            product.setStock(product.getStock() - itemDto.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItemRepository.save(orderItem);

            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(itemDto.getQuantity()));
            finalTotal = finalTotal.add(itemTotal);
        }

        savedOrder.setTotalAmount(finalTotal);
        savedOrder = orderRepository.save(savedOrder);

        return convertToDTO(savedOrder);
    }

    public OrderDTO cancelOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        if (order.getStatus().equals("Canceled")) {
            throw new RuntimeException("Order is already canceled!");
        }

        order.setStatus("Canceled");

        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }

        return convertToDTO(orderRepository.save(order));
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
            dto.setCustomerFullName(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
        }

        if (order.getOrderItems() != null) {
            List<OrderItemDTO> itemDTOs = order.getOrderItems().stream().map(item -> {
                OrderItemDTO itemDto = new OrderItemDTO();
                itemDto.setId(item.getId());
                itemDto.setQuantity(item.getQuantity());
                itemDto.setPrice(item.getPrice());
                itemDto.setOrderId(order.getId());
                if (item.getProduct() != null) {
                    itemDto.setProductId(item.getProduct().getId());
                    itemDto.setProductName(item.getProduct().getName());
                }
                return itemDto;
            }).collect(Collectors.toList());
            dto.setItems(itemDTOs);
        }
        return dto;
    }
}

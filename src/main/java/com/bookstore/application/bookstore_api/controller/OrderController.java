package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.exception.CustomerNotFoundException;
import com.bookstore.application.bookstore_api.exception.OrderNotFoundException;
import com.bookstore.application.bookstore_api.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/orders")
public class OrderController {

    private final List<Order> orders = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createOrder(@PathVariable String customerId, @RequestBody Order order) {
        order.setCustomerId(customerId);
        orders.add(order);
        return ResponseEntity.ok("Order placed successfully with ID: " + order.getOrderId());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable String customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        if (customerOrders.isEmpty()) {
            throw new CustomerNotFoundException("No orders found for customer ID: " + customerId);
        }
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String customerId, @PathVariable String orderId) {
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId) && order.getOrderId().equals(orderId)) {
                return ResponseEntity.ok(order);
            }
        }
        throw new OrderNotFoundException("Order with ID: " + orderId + " not found for customer ID: " + customerId);
    }
}

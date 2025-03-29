package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/orders")
public class OrderController {

    private final List<Order> orders = new ArrayList<>();


    @PostMapping
    public String createOrder(@PathVariable String customerId, @RequestBody Order order) {
        orders.add(order);
        return "Order placed successfully with ID: " + order.getOrderId();
    }


    @GetMapping
    public List<Order> getAllOrders(@PathVariable String customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable String customerId, @PathVariable String orderId) {
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId) && order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null; // Consider using ResponseEntity for better error handling
    }
}

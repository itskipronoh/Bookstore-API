package com.bookstore.application.bookstore_api.model;

import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private List<CartItem> items;
    private double totalPrice;
    private String orderDate;
    private String status;

    public Order(String orderId, String customerId, List<CartItem> items, double totalPrice, String orderDate, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setCustomerId(String customerId) {
    }
}

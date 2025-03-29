package com.bookstore.application.bookstore_api.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String customerId;
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Add an item to the cart
    public void addItem(CartItem item) {
        this.items.add(item);
    }

    // Remove an item by bookId
    public void removeItem(String bookId) {
        this.items.removeIf(item -> item.getBookId().equals(bookId));
    }

    // Update item quantity
    public void updateItem(String bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }
}

package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.exception.CartItemNotFoundException;
import com.bookstore.application.bookstore_api.exception.CustomerNotFoundException;
import com.bookstore.application.bookstore_api.model.Cart;
import com.bookstore.application.bookstore_api.model.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers/{customerId}/cart")
public class CartController {

    private final Map<String, Cart> customerCarts = new HashMap<>();

    // POST: Add a book to the customer's cart
    @PostMapping("/items")
    public ResponseEntity<String> addToCart(@PathVariable String customerId, @RequestBody CartItem item) {
        customerCarts.putIfAbsent(customerId, new Cart(customerId));
        customerCarts.get(customerId).addItem(item);
        return ResponseEntity.ok("Book added to cart successfully!");
    }

    // GET: Retrieve a customer's cart
    @GetMapping
    public ResponseEntity<Cart> getCart(@PathVariable String customerId) {
        if (!customerCarts.containsKey(customerId)) {
            throw new CustomerNotFoundException("Cart for customer ID: " + customerId + " not found.");
        }
        return ResponseEntity.ok(customerCarts.get(customerId));
    }

    // PUT: Update quantity of a book in the cart
    @PutMapping("/items/{bookId}")
    public ResponseEntity<String> updateCartItem(@PathVariable String customerId, @PathVariable String bookId, @RequestParam int quantity) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CustomerNotFoundException("Cart for customer ID: " + customerId + " not found.");
        }
        if (!cart.hasItem(bookId)) {
            throw new CartItemNotFoundException("Book with ID: " + bookId + " not found in cart.");
        }
        cart.updateItem(bookId, quantity);
        return ResponseEntity.ok("Book quantity updated successfully!");
    }

    // DELETE: Remove a book from the cart
    @DeleteMapping("/items/{bookId}")
    public ResponseEntity<String> removeFromCart(@PathVariable String customerId, @PathVariable String bookId) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CustomerNotFoundException("Cart for customer ID: " + customerId + " not found.");
        }
        if (!cart.hasItem(bookId)) {
            throw new CartItemNotFoundException("Book with ID: " + bookId + " not found in cart.");
        }
        cart.removeItem(bookId);
        return ResponseEntity.ok("Book removed from cart!");
    }
}

package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.Cart;
import com.bookstore.application.bookstore_api.model.CartItem;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers/{customerId}/cart")
public class CartController {

    private final Map<String, Cart> customerCarts = new HashMap<>();

    // POST: Add a book to the customer's cart
    @PostMapping("/items")
    public String addToCart(@PathVariable String customerId, @RequestBody CartItem item) {
        customerCarts.putIfAbsent(customerId, new Cart(customerId));
        customerCarts.get(customerId).addItem(item);
        return "Book added to cart successfully!";
    }

    // GET: Retrieve a customer's cart
    @GetMapping
    public Cart getCart(@PathVariable String customerId) {
        return customerCarts.getOrDefault(customerId, new Cart(customerId));
    }

    // PUT: Update quantity of a book in the cart
    @PutMapping("/items/{bookId}")
    public String updateCartItem(@PathVariable String customerId, @PathVariable String bookId, @RequestParam int quantity) {
        Cart cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.updateItem(bookId, quantity);
            return "Book quantity updated successfully!";
        }
        return "Cart not found!";
    }

    // DELETE: Remove a book from the cart
    @DeleteMapping("/items/{bookId}")
    public String removeFromCart(@PathVariable String customerId, @PathVariable String bookId) {
        Cart cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.removeItem(bookId);
            return "Book removed from cart!";
        }
        return "Cart not found!";
    }
}

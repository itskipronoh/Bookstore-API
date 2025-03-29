package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.bookVendor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookVendor")
public class bookApiService {

    private final List<bookVendor> books = new ArrayList<>(); // Store books in memory

    @PostMapping
    public String createBookDetails(@RequestBody bookVendor book) {
        books.add(book); // Add book to list
        return "Book created successfully with ID: " + book.getId();
    }

    @GetMapping("/{id}")
    public bookVendor getBookDetails(@PathVariable String id) {
        for (bookVendor book : books) {
            if (book.getId().equals(id)) {
                return book; // Return the stored book if found
            }
        }
        return null; // Return null if not found (Consider using ResponseEntity)
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable String id, @RequestBody bookVendor updatedBook) {
        for (bookVendor book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthorId(updatedBook.getAuthorId());
                book.setIsbn(updatedBook.getIsbn());
                book.setPublicationYear(updatedBook.getPublicationYear());
                book.setPrice(updatedBook.getPrice());
                book.setStockQuantity(updatedBook.getStockQuantity());
                return "Book updated successfully with ID: " + id;
            }
        }
        return "Book not found with ID: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteBookDetails(@PathVariable String id) {
        books.removeIf(book -> book.getId().equals(id)); // Remove book directly
        return "Book deleted successfully with ID: " + id;
    }

}

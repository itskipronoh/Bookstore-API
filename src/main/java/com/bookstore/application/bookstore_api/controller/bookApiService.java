package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.BookResource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books") // Base URL for all book endpoints
public class bookApiService {

    private final List<BookResource> books = new ArrayList<>(); // Store books in memory

    // POST: Create a new book
    @PostMapping
    public String createBook(@RequestBody BookResource book) {
        books.add(book);
        return "Book created successfully with ID: " + book.getId();
    }

    // GET: Retrieve all books
    @GetMapping
    public List<BookResource> getAllBooks() {
        return books;
    }

    // GET: Retrieve a book by ID
    @GetMapping("/{id}")
    public BookResource getBookById(@PathVariable String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null); // Consider using ResponseEntity for better handling
    }

    // PUT: Update a book by ID
    @PutMapping("/{id}")
    public String updateBook(@PathVariable String id, @RequestBody BookResource updatedBook) {
        for (BookResource book : books) {
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

    // DELETE: Remove a book by ID
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) {
        return books.removeIf(book -> book.getId().equals(id)) ?
                "Book deleted successfully with ID: " + id :
                "Book not found with ID: " + id;
    }
}

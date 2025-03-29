package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.Book;
import com.bookstore.application.bookstore_api.exception.BookNotFoundException;
import com.bookstore.application.bookstore_api.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new InvalidInputException("Book title is required.");
        }
        books.add(book);
        return new ResponseEntity<>("Book created successfully with ID: " + book.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable String id, @RequestBody Book updatedBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthorId(updatedBook.getAuthorId());
                book.setIsbn(updatedBook.getIsbn());
                book.setPublicationYear(updatedBook.getPublicationYear());
                book.setPrice(updatedBook.getPrice());
                book.setStockQuantity(updatedBook.getStockQuantity());
                return ResponseEntity.ok("Book updated successfully.");
            }
        }
        throw new BookNotFoundException("Book with ID " + id + " not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));
        if (!removed) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return ResponseEntity.noContent().build();
    }
}

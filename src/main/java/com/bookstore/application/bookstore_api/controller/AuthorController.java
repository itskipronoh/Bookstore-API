package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.exception.AuthorNotFoundException;
import com.bookstore.application.bookstore_api.model.Author;
import com.bookstore.application.bookstore_api.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final List<Author> authors = new ArrayList<>();
    private final List<Book> books = new ArrayList<>(); // Simulating book storage


    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody Author author) {
        if (author.getName() == null || author.getName().isEmpty()) {
            throw new IllegalArgumentException("Author name is required.");
        }
        authors.add(author);
        return new ResponseEntity<>("Author created successfully with ID: " + author.getId(), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authors);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found."));
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable String id, @RequestBody Author updatedAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId().equals(id)) {
                authors.set(i, updatedAuthor);
                return ResponseEntity.ok("Author updated successfully with ID: " + id);
            }
        }
        throw new AuthorNotFoundException("Author with ID " + id + " not found.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        boolean removed = authors.removeIf(author -> author.getId().equals(id));
        if (!removed) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String id) {
        if (authors.stream().noneMatch(author -> author.getId().equals(id))) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }

        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorId().equals(id)) {
                authorBooks.add(book);
            }
        }
        return ResponseEntity.ok(authorBooks);
    }
}

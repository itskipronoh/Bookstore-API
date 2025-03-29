package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.Author;
import com.bookstore.application.bookstore_api.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final List<Author> authors = new ArrayList<>();
    private final List<Book> books = new ArrayList<>(); // This should be fetched from the book service

    // 1. POST /authors - Create a new author
    @PostMapping
    public String createAuthor(@RequestBody Author author) {
        authors.add(author);
        return "Author created successfully with ID: " + author.getId();
    }

    // 2. GET /authors - Retrieve all authors
    @GetMapping
    public List<Author> getAllAuthors() {
        return authors;
    }

    // 3. GET /authors/{id} - Retrieve an author by ID
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable String id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 4. PUT /authors/{id} - Update an existing author
    @PutMapping("/{id}")
    public String updateAuthor(@PathVariable String id, @RequestBody Author updatedAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId().equals(id)) {
                authors.set(i, updatedAuthor);
                return "Author updated successfully with ID: " + id;
            }
        }
        return "Author not found!";
    }

    // 5. DELETE /authors/{id} - Delete an author by ID
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable String id) {
        authors.removeIf(author -> author.getId().equals(id));
        return "Author deleted successfully!";
    }

    // 6. GET /authors/{id}/books - Retrieve books written by the author
    @GetMapping("/{id}/books")
    public List<Book> getBooksByAuthor(@PathVariable String id) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorId().equals(id)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
}

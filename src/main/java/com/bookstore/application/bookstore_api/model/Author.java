package com.bookstore.application.bookstore_api.model;

import java.util.List;

public class Author {
    private String id;
    private String name;
    private String biography;
    private List<String> bookIds; // List of book IDs written by the author

    public Author() {}

    public Author(String id, String name, String biography, List<String> bookIds) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.bookIds = bookIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<String> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }
}

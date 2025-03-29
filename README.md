# Bookstore API

## 📖 Overview

The **Bookstore API** is a RESTful web service for managing a bookstore. It allows users to interact with books, authors, customers, carts, and orders efficiently. The API provides endpoints for CRUD operations, user authentication, and exception handling.

## 🚀 Features

- **Books Management**: Create, retrieve, update, and delete books.
- **Authors Management**: Manage author details.
- **Customer Management**: Handle customer data.
- **Cart System**: Add books to a cart before purchase.
- **Orders Management**: Process orders from customer carts.
- **Exception Handling**: Graceful error handling with custom exceptions.

## 🛠️ Tech Stack

- **Java** (Spring Boot Framework)
- **JAR-RS** (REST API development)
- **H2** (Memory storage)


## ⚙️ Setup & Installation

### Prerequisites

- Java 17+
- Maven
- Git

### Steps

1. **Clone the Repository**
   ```sh
   git clone https://github.com/itskipronoh/Bookstore-API.git
   cd Bookstore-API
   ```
2. **Configure Memory location**
   - Update `application.properties` for  H2.
3. **Build and Run the Application**
   ```sh
   mvn spring-boot:run
   ```

## 📌 API Endpoints

### **Books**

- `POST /books` - Add a new book
- `GET /books` - Retrieve all books
- `GET /books/{id}` - Get a specific book
- `PUT /books/{id}` - Update a book
- `DELETE /books/{id}` - Remove a book

### **Authors**

- `POST /authors` - Add a new author
- `GET /authors` - Retrieve all authors
- `GET /authors/{id}` - Get an author
- `PUT /authors/{id}` - Update an author
- `DELETE /authors/{id}` - Remove an author

### **Customers**

- `POST /customers` - Create a new customer
- `GET /customers` - Retrieve all customers
- `GET /customers/{id}` - Get a customer
- `PUT /customers/{id}` - Update customer info
- `DELETE /customers/{id}` - Delete a customer

### **Cart**

- `POST /customers/{customerId}/cart` - Add books to cart
- `GET /customers/{customerId}/cart` - View cart items
- `DELETE /customers/{customerId}/cart` - Clear cart

### **Orders**

- `POST /customers/{customerId}/orders` - Place an order
- `GET /customers/{customerId}/orders` - View orders
- `GET /customers/{customerId}/orders/{orderId}` - Get order details

## 🛑 Exception Handling

- `BookNotFoundException` - If a book is not found
- `AuthorNotFoundException` - If an author is missing
- `CustomerNotFoundException` - If a customer does not exist
- `CartNotFoundException` - If a cart is empty
- `OutOfStockException` - If a book is unavailable
- `InvalidInputException` - For incorrect request data

## 📝 License

This project is licensed under the **MIT License**.

## 🤝 Contribution

Feel free to fork the repo, open an issue, or submit a pull request!

## 📬 Contact

**GitHub**: [@itskipronoh](https://github.com/itskipronoh)

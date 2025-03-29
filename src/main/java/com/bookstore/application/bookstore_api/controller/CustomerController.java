package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    // POST: Create a new customer
    @PostMapping
    public String createCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Customer created successfully with ID: " + customer.getId();
    }

    // GET: Retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // GET: Retrieve a single customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // PUT: Update an existing customer
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                customer.setName(updatedCustomer.getName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPassword(updatedCustomer.getPassword());
                return "Customer updated successfully!";
            }
        }
        return "Customer not found!";
    }

    // DELETE: Remove a customer by ID
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id) {
        customers.removeIf(customer -> customer.getId().equals(id));
        return "Customer deleted successfully!";
    }
}

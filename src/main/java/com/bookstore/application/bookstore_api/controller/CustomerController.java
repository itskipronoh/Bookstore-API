package com.bookstore.application.bookstore_api.controller;

import com.bookstore.application.bookstore_api.exception.CustomerNotFoundException;
import com.bookstore.application.bookstore_api.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();


    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Customer email is required.");
        }
        customers.add(customer);
        return new ResponseEntity<>("Customer created successfully with ID: " + customer.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customers);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                customer.setName(updatedCustomer.getName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setPassword(updatedCustomer.getPassword());
                return ResponseEntity.ok("Customer updated successfully with ID: " + id);
            }
        }
        throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        boolean removed = customers.removeIf(customer -> customer.getId().equals(id));
        if (!removed) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return ResponseEntity.noContent().build();
    }
}

package com.example.orders.services;

import com.example.orders.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomersAndOrders();

    Customer findCustomerById(long id);
    Customer save(Customer customer);
}

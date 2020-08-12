package com.example.orders.services;

import com.example.orders.OrderCounts;
import com.example.orders.models.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomersAndOrders();
    List<Customer> findByCustname(String custname);
    List<OrderCounts> getOrderCount();
    Customer findCustomerById(long id);
    Customer save(Customer customer);

}

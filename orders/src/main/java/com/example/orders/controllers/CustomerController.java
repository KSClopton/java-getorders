package com.example.orders.controllers;

import com.example.orders.models.Customer;
import com.example.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerServices;

    // http://localhost:2019/customers/orders - returns all customers and orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomersAndOrders(){
        List<Customer> myList = customerServices.findAllCustomersAndOrders();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
    // http://localhost:2019/customers/customer/id - returns the customer and their orders with the given customer id
    @GetMapping(value = "customers/{customerid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long customerid){
        Customer myCustomer = customerServices.findCustomerById(customerid);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike -returns all customers and their orders with a customer name containing the given substring
//    @GetMapping(value = "customers/" , produces = "application/json)
    // http://localhost:2019/customers/orders/count - using a query return a list of all customers with the number or orders they have placed.

}

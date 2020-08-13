package com.example.orders.controllers;

import com.example.orders.OrderCounts;
import com.example.orders.models.Customer;
import com.example.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @GetMapping(value = "/customer/{customerid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long customerid){
        Customer myCustomer = customerServices.findCustomerById(customerid);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike -returns all customers and their orders with a customer name containing the given substring
    @GetMapping(value = "/{namelike}", produces = "application/json")
    public ResponseEntity<?> findCustomersByNameLike(@PathVariable String custname){
        List<Customer> myList = customerServices.findByCustname(custname);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders/count - using a query return a list of all customers with the number of orders they have placed.
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getOrderCount(){
        List<OrderCounts> myList = customerServices.getOrderCount();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer - Adds a new customer and orders
    // POST Request Body -> New Customer =>
    @PostMapping(value = "/customer", consumes = "applications/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);

        // return location of new customer
        // location => http://localhost:2019/customers/customer/id
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newCustomer.getCustcode())
                .build()
                .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/customers/customer/{custcode} - completely replaces the customer record including associated orders with the provided data
    // PUT Request Body -> All the information
    @PutMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updateCustomer, @PathVariable long custcode){
        
    }

    // http://localhost:2019/customers/customer/{custcode} - updates customers with the new data.

    // http://localhost:2019/customers/customer/{custcode} - Deletes the given customer including any associated orders


}

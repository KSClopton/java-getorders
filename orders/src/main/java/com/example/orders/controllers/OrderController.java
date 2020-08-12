package com.example.orders.controllers;

import com.example.orders.models.Order;
import com.example.orders.services.OrderService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderServices;
    // http://localhost:2019/orders/order/id - returns the order and it's customer with the given order number
    @GetMapping(value = "order/{orderid}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long orderid){
        Order myOrder = orderServices.findOrderById(orderid);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }
}

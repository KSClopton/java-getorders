package com.example.orders.services;

import com.example.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "orderServices")
public class OrderServiceImp implements OrderService{
    @Autowired
    OrderRepository orderRepository;
}

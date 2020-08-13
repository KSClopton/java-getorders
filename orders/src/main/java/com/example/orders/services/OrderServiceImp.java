package com.example.orders.services;

import com.example.orders.models.Order;
import com.example.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "orderServices")
public class OrderServiceImp implements OrderService{
    @Autowired
    OrderRepository orderrepos;

    @Override
    public Order findOrderById(long id) {
        return orderrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found!"));
    }

    @Override
    public Order save(Order order) {
        return orderrepos.save(order);
    }
}

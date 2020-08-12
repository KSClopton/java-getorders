package com.example.orders.services;

import com.example.orders.models.Customer;
import com.example.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerServices")
public class CustomerServiceImp implements CustomerService{
    @Autowired
    CustomerRepository custrepos;

    @Override
    public Customer save(Customer customer){
        return custrepos.save(customer);
    }

    @Override
    public List<Customer> findAllCustomersAndOrders() {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }
}

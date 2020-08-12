package com.example.orders.repositories;

import com.example.orders.OrderCounts;
import com.example.orders.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustnameContainingIgnoringCase(String custname);

    @Query(value = "SELECT c.custname as name, count(o.ordnum) as QTY " +
            "FROM customers c LEFT join orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY QTY desc", nativeQuery = true)
    List<OrderCounts> findOrderCounts();
}

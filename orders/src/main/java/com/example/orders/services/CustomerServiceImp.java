package com.example.orders.services;

import com.example.orders.OrderCounts;
import com.example.orders.models.Customer;
import com.example.orders.models.Order;
import com.example.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerRepository custrepos;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());

        // many to many
        // payment must already exist


        // one to many
        newCustomer.getOrders().clear();
        for(Order o : customer.getOrders()){
            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setCustomer(newCustomer);

            newCustomer.getOrders().add(newOrder);
        }
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

    @Override
    public List<Customer> findByCustname(String custname) {
        return custrepos.findByCustnameContainingIgnoringCase(custname);
    }

    @Override
    public List<OrderCounts> getOrderCount() {
        List<OrderCounts> list = custrepos.findOrderCounts();
        return list;
    }
}

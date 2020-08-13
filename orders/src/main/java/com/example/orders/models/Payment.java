package com.example.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(nullable = false)
    private Long paymentid;

    @Column(nullable = false)
    private String type;

    @ManyToMany(mappedBy = "payments")
    @JsonIgnoreProperties("payments")
    private Set<Order> orders = new HashSet<>();

    public Payment() {
    }

    public Payment(String type) {
        this.type = type;
    }

    public Long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Long paymentid) {
        this.paymentid = paymentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

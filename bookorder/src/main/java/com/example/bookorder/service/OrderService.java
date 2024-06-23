package com.example.bookorder.service;

import com.example.bookorder.model.Order;
import com.example.bookorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void processBookOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

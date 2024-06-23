package com.example.bookorder.controller;

import com.example.bookorder.model.Order;
import com.example.bookorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void createBookOrder(@RequestBody List<Order> orders) {
        orderService.processBookOrders(orders);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

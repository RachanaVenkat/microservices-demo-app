package com.example.order_service.services;

import com.example.order_service.models.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // ✅ Add this method to fetch all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

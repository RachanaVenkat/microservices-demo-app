package com.example.order_service.repository;
import com.example.order_service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long> {}
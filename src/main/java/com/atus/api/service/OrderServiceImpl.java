package com.atus.api.service;

import com.atus.api.entity.OrderEntity;
import com.atus.api.model.NewOrder;
import com.atus.api.repository.OrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.util.Strings;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderEntity> addOrder(@Valid NewOrder newOrder) {
        if (Strings.isEmpty(newOrder.getCustomerId())) {
            throw new RuntimeException("Invalid customer id");
        }

        return orderRepository.insert(newOrder);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId) {
        return orderRepository.findByCustomerId(UUID.fromString(customerId));
    }

    @Override
    public Optional<OrderEntity> getOrdersByOrderId(String orderId) {
        return orderRepository.findById(UUID.fromString(orderId));
    }
}

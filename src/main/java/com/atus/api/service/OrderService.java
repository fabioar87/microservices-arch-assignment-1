package com.atus.api.service;

import com.atus.api.entity.OrderEntity;
import com.atus.api.model.NewOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface OrderService {
    Optional<OrderEntity> addOrder(@Valid NewOrder newOrder);
    Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);
    Optional<OrderEntity> getOrdersByOrderId(String orderId);
}

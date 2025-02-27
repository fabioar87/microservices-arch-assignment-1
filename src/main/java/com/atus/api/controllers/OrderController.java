package com.atus.api.controllers;

import com.atus.api.OrderApi;
import com.atus.api.hateoas.OrderRepresentationModelAssembler;
import com.atus.api.model.NewOrder;
import com.atus.api.model.Order;
import com.atus.api.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController implements OrderApi {

    public final OrderService orderService;
    public final OrderRepresentationModelAssembler assembler;

    public OrderController(OrderService orderService, OrderRepresentationModelAssembler assembler) {
        this.orderService = orderService;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Order> addOrder(@Valid NewOrder newOrder) {
        return orderService.addOrder(newOrder).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@Valid @NotNull String customerId) throws Exception {
        return ResponseEntity.ok(assembler.toListModel(orderService.getOrdersByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<Order> getOrdersByOrderId(String orderId) throws Exception {
        return orderService.getOrdersByOrderId(orderId).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

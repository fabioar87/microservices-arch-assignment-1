package com.atus.api.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "ORDER_ID")
    private UUID orderId;

    @Column(name = "ITEM_ID")
    private UUID itemId;
}

package com.atus.api.entity;

import com.atus.api.model.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Order.StatusEnum status;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    private CustomerEntity userEntity;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ORDER_ITEM",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> items = new ArrayList<>();
}

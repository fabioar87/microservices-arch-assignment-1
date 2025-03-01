package com.atus.api.entity;

import com.atus.api.model.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "\"order\"")
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
    private CustomerEntity customerEntity;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ORDER_ITEM",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> items = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public OrderEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderEntity setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public Order.StatusEnum getStatus() {
        return status;
    }

    public OrderEntity setStatus(Order.StatusEnum status) {
        this.status = status;
        return this;
    }

    public CustomerEntity getUserEntity() {
        return customerEntity;
    }

    public OrderEntity setUserEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
        return this;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public OrderEntity setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public OrderEntity setItems(List<ItemEntity> items) {
        this.items = items;
        return this;
    }
}

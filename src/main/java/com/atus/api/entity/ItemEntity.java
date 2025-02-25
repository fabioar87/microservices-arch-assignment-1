package com.atus.api.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}

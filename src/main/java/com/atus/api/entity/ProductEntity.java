package com.atus.api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BASE_PRICE")
    private BigDecimal basePrice;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @OneToMany(mappedBy = "product")
    private List<ItemEntity> items;
}

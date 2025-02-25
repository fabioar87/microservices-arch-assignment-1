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

    public UUID getId() {
        return id;
    }

    public ProductEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public ProductEntity setBasePrice(BigDecimal price) {
        this.basePrice = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<ItemEntity> getItem() {
        return items;
    }

    public ProductEntity setItem(List<ItemEntity> item) {
        this.items = item;
        return this;
    }
}

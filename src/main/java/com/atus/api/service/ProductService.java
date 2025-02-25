package com.atus.api.service;

import com.atus.api.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface ProductService {
    @NotNull Iterable<ProductEntity> getAllProducts();
    Optional<ProductEntity> getProductById(String productId);
}

package com.atus.api.service;

import com.atus.api.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    @NotNull Iterable<ProductEntity> getAllProducts(Pageable pageable);
    Optional<ProductEntity> getProductById(String productId);
}

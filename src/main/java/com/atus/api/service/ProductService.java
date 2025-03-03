package com.atus.api.service;

import com.atus.api.dto.ProductDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    @NotNull Iterable<ProductDTO> getAllProducts(Pageable pageable);
    Optional<ProductDTO> getProductById(String productId);
}

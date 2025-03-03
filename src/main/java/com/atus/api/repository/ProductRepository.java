package com.atus.api.repository;

import com.atus.api.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findById(UUID id);
}
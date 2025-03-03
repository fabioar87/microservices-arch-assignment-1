package com.atus.api.service;

import com.atus.api.dto.ProductDTO;
import com.atus.api.entity.ProductEntity;
import com.atus.api.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(String productId) {
        return productRepository.findById(UUID.fromString(productId))
                .stream()
                .map(this::convertEntityToDTO)
                .findFirst();
    }

    private ProductDTO convertEntityToDTO(ProductEntity product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setBasePrice(product.getBasePrice());
        productDTO.getImageUrl();
        return productDTO;
    }
}

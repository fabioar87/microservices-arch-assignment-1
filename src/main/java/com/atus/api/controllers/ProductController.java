package com.atus.api.controllers;

import com.atus.api.ProductApi;
import com.atus.api.hateoas.ProductRepresentationModelAssembler;
import com.atus.api.model.Product;
import com.atus.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;
    private final ProductRepresentationModelAssembler assembler;

    public ProductController(ProductService productService, ProductRepresentationModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<List<Product>> queryProducts(@Valid String name,
                                                       @Valid Integer page,
                                                       @Valid Integer size) throws Exception {

        return ResponseEntity.ok(assembler.toListModel(productService.getAllProducts()));
    }

    @Override
    public ResponseEntity<Product> getProductById(String productId) throws Exception {
        return productService.getProductById(productId).map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
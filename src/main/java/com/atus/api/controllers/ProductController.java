package com.atus.api.controllers;

import com.atus.api.ProductApi;
import com.atus.api.model.Product;
import com.atus.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts(String name, Integer page, Integer size) throws Exception {
        return ProductApi.super.getAllProducts(name, page, size);
    }

    @Override
    public ResponseEntity<Product> getProductById(String id) throws Exception {
        return ProductApi.super.getProductById(id);
    }
}
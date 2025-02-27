package com.atus.api.hateoas;

import com.atus.api.controllers.ProductController;
import com.atus.api.entity.ProductEntity;
import com.atus.api.model.Product;
import com.atus.api.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<ProductEntity, Product> {

    public ProductRepresentationModelAssembler(ProductService productService) {
        super(ProductController.class, Product.class);
    }

    @Override
    public Product toModel(ProductEntity entity) {
        Product resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());

        try {
            resource.add(
                    linkTo(methodOn(ProductController.class).getProductById(entity.getId().toString()))
                            .withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resource;
    }
}

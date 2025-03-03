package com.atus.api.hateoas;

import com.atus.api.controllers.ProductController;
import com.atus.api.dto.ProductDTO;
import com.atus.api.model.Product;
import com.atus.api.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<ProductDTO, Product> {

    public ProductRepresentationModelAssembler(ProductService productService) {
        super(ProductController.class, Product.class);
    }

    @Override
    public Product toModel(ProductDTO entity) {
        Product resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());

        try {
            resource.add(
                    linkTo(methodOn(ProductController.class).getProductById(entity.getId().toString()))
                            .withSelfRel());

            resource.add(
                    linkTo(methodOn(ProductController.class).queryProducts(entity.getName(), 1, 10))
                            .withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resource;
    }

    public List<Product> toListModel(Iterable<ProductDTO> entities) {
        if(Objects.isNull(entities)) {
            return List.of();
        }

        return StreamSupport.stream(entities.spliterator(), false).map(this::toModel).collect(toList());
    }
}

package com.atus.api.hateoas;

import com.atus.api.controllers.CustomerController;
import com.atus.api.entity.CustomerEntity;
import com.atus.api.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<CustomerEntity, Customer> {

    public CustomerRepresentationModelAssembler() {
        super(CustomerController.class, Customer.class);
    }

    @Override
    public Customer toModel(CustomerEntity entity) {
        Customer resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());

        try {
            resource.add(
                    linkTo(methodOn(CustomerController.class).getCustomerById(entity.getId().toString()))
                            .withSelfRel()
            );

            resource.add(
                    linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resource;
    }

    public List<Customer> toListModel(Iterable<CustomerEntity> entities) {
        if (Objects.isNull(entities)) {
            return List.of();
        }

        return StreamSupport.stream(entities.spliterator(), false).map(this::toModel)
                .collect(toList());
    }
}

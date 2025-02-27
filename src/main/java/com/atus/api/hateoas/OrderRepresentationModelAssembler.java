package com.atus.api.hateoas;

import com.atus.api.controllers.OrderController;
import com.atus.api.entity.OrderEntity;
import com.atus.api.model.Order;
import com.atus.api.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<OrderEntity, Order> {

    private final CustomerRepresentationModelAssembler customerAssembler;
    private final ItemService itemService;

    public OrderRepresentationModelAssembler(CustomerRepresentationModelAssembler customerAssembler,
                                             ItemService itemService) {
        super(OrderController.class, Order.class);
        this.customerAssembler = customerAssembler;
        this.itemService = itemService;
    }

    @Override
    public Order toModel(OrderEntity entity) {
        Order resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.id(entity.getId().toString())
                .customer(customerAssembler.toModel(entity.getUserEntity()))
                .items(itemService.toModelList(entity.getItems()))
                .date(entity.getOrderDate().toInstant().atOffset(ZoneOffset.UTC));

        try {
            resource.add(linkTo(methodOn(OrderController.class).getOrdersByOrderId(entity.getId().toString()))
                    .withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resource;
    }

    public List<Order> toListModel(Iterable<OrderEntity> orderEntities) {
        if(Objects.isNull(orderEntities)) {
            return List.of();
        }

        return StreamSupport.stream(orderEntities.spliterator(), false).map(this::toModel)
                .collect(toList());
    }
}

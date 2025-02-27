package com.atus.api.repository;

import com.atus.api.entity.OrderEntity;
import com.atus.api.model.NewOrder;

import java.util.Optional;

public interface OrderRepositoryExtension {

    Optional<OrderEntity> insert(NewOrder m);
}

package com.atus.api.repository;

import com.atus.api.entity.OrderEntity;
import com.atus.api.model.NewOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {

    @Query("select o from OrderEntity o join o.customerEntity u where u.id = :customerId")
    Iterable<OrderEntity> findByCustomerId(UUID customerId);

    Optional<OrderEntity> insert(NewOrder m);
}

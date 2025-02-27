package com.atus.api.repository;


import com.atus.api.entity.OrderEntity;
import com.atus.api.model.NewOrder;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExtension {
    @Override
    public Optional<OrderEntity> insert(NewOrder order) {
        return Optional.empty();
    }
}

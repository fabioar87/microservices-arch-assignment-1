package com.atus.api.repository;

import com.atus.api.entity.OrderEntity;
import com.atus.api.entity.OrderItemEntity;
import com.atus.api.entity.ProductEntity;
import com.atus.api.model.Item;
import com.atus.api.model.NewOrder;
import com.atus.api.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExtension {

    @PersistenceContext private final EntityManager em;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderRepositoryImpl(EntityManager em, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.em = em;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Optional<OrderEntity> insert(NewOrder newOrder) {

        BigDecimal total = BigDecimal.ZERO;
        for(Item item : newOrder.getItems()) {
            ProductEntity productEntity =
                    (ProductEntity) em.createNativeQuery(
                    """
                           SELECT p.* FROM customerservice.product p WHERE p.id = ?
                       """, ProductEntity.class)
                    .setParameter(1, item.getId());

            total = (BigDecimal.valueOf(item.getQuantity())).multiply(productEntity.getBasePrice()).add(total);
        }

        Timestamp orderDate = Timestamp.from(Instant.now());
        em.createNativeQuery(
     """
            INSERT INTO customerservice.order (customer_id, order_date, total, order_status)
            VALUES (?, ?, ?, ?)
        """)
        .setParameter(1, newOrder.getCustomerId())
        .setParameter(2, orderDate)
        .setParameter(3, total)
        .setParameter(4, Order.StatusEnum.CREATED.getValue())
        .executeUpdate();

        OrderEntity orderEntity =
            (OrderEntity) em.createNativeQuery(
            """
                SELECT o.* FROM customerservice.order o WHERE o.customer_id = ? AND o.order_date >= ?
               """, OrderEntity.class)
                .setParameter(1, newOrder.getCustomerId())
                .setParameter(2, OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z"))
                        .truncatedTo(ChronoUnit.MICROS))
            .getSingleResult();

        orderItemRepository.saveAll(
            newOrder.getItems().stream()
                .map(i -> new OrderItemEntity().setOrderId(orderEntity.getId()).setItemId(UUID.fromString(i.getId())))
                .collect(toList())
        );

        return Optional.of(orderEntity);
    }
}

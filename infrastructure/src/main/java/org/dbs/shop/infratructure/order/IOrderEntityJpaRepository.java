package org.dbs.shop.infratructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderEntityJpaRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerUserName(String userName);
    OrderEntity findByNumber(int orderNumber);
}

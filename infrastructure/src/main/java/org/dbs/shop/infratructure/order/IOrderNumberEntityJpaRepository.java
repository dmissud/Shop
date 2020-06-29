package org.dbs.shop.infratructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderNumberEntityJpaRepository extends JpaRepository<OrderNumberEntity, Long> {
    OrderNumberEntity findByName(String name);
}

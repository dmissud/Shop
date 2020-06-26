package org.dbs.shop.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryJpaOrder extends JpaRepository<OrderEntity, Long> {

	OrderEntity findByCustomerUserName(String customerName);

}

package org.dbs.shop.infrastructure.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryJpaOrder extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findByCustomerUserName(String customerName);

}

package org.dbs.shop.infrastructure.customer;

import org.dbs.shop.infrastructure.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

	CustomerEntity findByUserName(String name);

}

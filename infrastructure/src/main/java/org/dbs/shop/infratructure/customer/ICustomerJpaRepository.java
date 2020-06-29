package org.dbs.shop.infratructure.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByUserName(String name);
}

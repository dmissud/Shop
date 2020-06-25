package org.dbs.shop.domain;

public interface IRepositoryCustomer {
    void save(Customer customer);
    Customer findByName(String customerName);
}

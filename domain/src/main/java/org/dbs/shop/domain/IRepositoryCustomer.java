package org.dbs.shop.domain;

import java.util.List;

public interface IRepositoryCustomer {
    void save(Customer customer);
    Customer findByName(String customerName);

    List<Customer> retrieveAllCustomers();
}

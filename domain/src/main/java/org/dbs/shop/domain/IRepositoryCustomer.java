package org.dbs.shop.domain;

public interface IRepositoryCustomer {
    void save(Customer customer) throws CustomerAllReadyExistException;

    Customer findByName(String customerName) throws CustomerNotFoundException;
}

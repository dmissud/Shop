package org.dbs.shop.domain.shop;

import java.util.List;

public interface IRepositoryCustomer {
    int giveNextCustomerNumber();
    void save(Customer customer);
    Customer findByNumber(int number);
    Customer findByName(String customerName);
    List<Customer> retrieveAllCustomers();
}

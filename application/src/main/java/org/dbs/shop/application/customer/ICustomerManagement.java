package org.dbs.shop.application.customer;

import org.dbs.shop.domain.Customer;

import java.util.List;

public interface ICustomerManagement {
    Customer referenceCustomer(String customerName);

    Customer retrieveCustomerByName(String customerName);

    List<Customer> retrieveAllCustomers();
}

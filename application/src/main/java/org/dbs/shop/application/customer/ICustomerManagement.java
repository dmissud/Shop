package org.dbs.shop.application.customer;

import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.user.User;

import java.util.List;

public interface ICustomerManagement {
    Customer referenceCustomer(String customerName, String email);

    Customer retrieveCustomerByName(String customerName);

    List<Customer> retrieveAllCustomers();
}

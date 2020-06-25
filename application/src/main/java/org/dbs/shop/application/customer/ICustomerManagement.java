package org.dbs.shop.application.customer;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAlreadyExistException;
import org.dbs.shop.domain.NotFoundException;

public interface ICustomerManagement {
    void referenceCustomer(String customerName) throws CustomerAlreadyExistException;
    Customer retrieveCustomerByName(String customerName) throws NotFoundException;
}

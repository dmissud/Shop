package org.dbs.shop.application.customer;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAllReadyExistException;
import org.dbs.shop.domain.CustomerNotFoundException;

public interface ICustomerManagement {
    void referenceCustomer(String customerName) throws CustomerAllReadyExistException;
    Customer retrieveCustomerByName(String customerName) throws CustomerNotFoundException;
}

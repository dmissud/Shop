package org.dbs.shop.application.customer;

import org.dbs.shop.domain.CustomerAllReadyExistException;

public interface ICustomerManagement {
    public void referenceCustomer(String customerName) throws CustomerAllReadyExistException;
}

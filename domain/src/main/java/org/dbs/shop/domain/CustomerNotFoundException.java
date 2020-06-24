package org.dbs.shop.domain;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String customerName) {
        super("Customer not in repository" + customerName);
    }
}

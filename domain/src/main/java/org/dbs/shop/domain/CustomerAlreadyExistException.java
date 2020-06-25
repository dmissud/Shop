package org.dbs.shop.domain;

public class CustomerAlreadyExistException extends Exception {
    public CustomerAlreadyExistException(String customerName) {
        super("The customer allready exist : "+customerName);
    }
}

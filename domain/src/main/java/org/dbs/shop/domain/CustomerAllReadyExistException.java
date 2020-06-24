package org.dbs.shop.domain;

public class CustomerAllReadyExistException extends Exception {
    public CustomerAllReadyExistException(String customerName) {
        super("The customer allready exist : "+customerName);
    }
}

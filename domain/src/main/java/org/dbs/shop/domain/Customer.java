package org.dbs.shop.domain;

import java.util.List;

public class Customer {
    private final String name;

    private String password;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

}

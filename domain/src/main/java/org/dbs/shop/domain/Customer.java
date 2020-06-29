package org.dbs.shop.domain;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private final String name;

    private String password;
    private Map<Integer, Order> orders;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.orders = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    public void placeOrder(Order order) {
        orders.put(order.getNumber(), order);
        order.setCustomer(this);

    }

    public int numberOfOrders() {
        return orders.size();
    }

    public Order retrieveOrder(int number) {
        return orders.get(number);
    }
}

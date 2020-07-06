package org.dbs.shop.domain.shop;

import org.apache.logging.log4j.util.StringBuilders;
import org.dbs.shop.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final int number;
    private final User user;
    private List<Order> orders;

    public Customer(int number, User user) {
        this.number = number;
        this.user = user;
        this.orders = new ArrayList<>();
    }

    void placeOrder(Order order) {
        this.orders.add(order);
    }

    public String giveUserName() {
        return user.getName();
    }

    public int numberOfOrders() {
        return orders.size();
    }

    public Order retrieveOrder(int number) {
        Order resultOrder = null;
        for(Order order:orders) {
            if (order.getNumber() == number) {
                resultOrder = order;
                break;
            }
        }
        return resultOrder;
    }

    public int getNumber() {
        return number;
    }

    public String getUserName() {
        return getUserName();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getName() {
        return user.getName();
    }
}

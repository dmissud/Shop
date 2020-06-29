package org.dbs.shop.domain;

import java.util.List;

public interface IRepositoryOrder {
    int giveNextOrderNumber();
    void save(Order order);
    List<Order> findOrderForCustomer(String customerName);
    Order findOrderByNumber(int numOrder);

    List<Order> findAllOrders();
}

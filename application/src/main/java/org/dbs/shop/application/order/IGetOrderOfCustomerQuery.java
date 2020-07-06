package org.dbs.shop.application.order;

import org.dbs.shop.domain.shop.Order;

import java.util.List;

public interface IGetOrderOfCustomerQuery {
    List<Order> getOrderOfCustomer(String customerName);

    Order getOrderOfCustomerByNumber(int orderNumber);

    List<Order> getAllOrders();
}

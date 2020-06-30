package org.dbs.shop.application.order;

import java.util.List;

import org.dbs.shop.domain.Item;
import org.dbs.shop.domain.order.Order;

public interface IOrderManagement {

	Order createOrder(String customerName, List<Item> items);

	List<Order> listCustomerOrders(String customerName);

}

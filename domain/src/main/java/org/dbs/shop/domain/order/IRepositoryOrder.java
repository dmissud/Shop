package org.dbs.shop.domain.order;

import java.util.List;

public interface IRepositoryOrder {

	Order save(Order order);

	List<Order> getCustomerOrders(String customerName);

}

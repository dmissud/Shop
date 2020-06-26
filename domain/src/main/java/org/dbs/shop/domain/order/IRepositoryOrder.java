package org.dbs.shop.domain.order;

public interface IRepositoryOrder {

	Order save(Order order);

	Order getCustomerOrders(String customerName);

}

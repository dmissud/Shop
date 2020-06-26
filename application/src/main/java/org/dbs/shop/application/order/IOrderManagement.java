package org.dbs.shop.application.order;

import java.util.List;

import org.dbs.shop.domain.Item;
import org.dbs.shop.domain.Order;

public interface IOrderManagement {

	Order createOrder(String customerName, List<Item> items);

}

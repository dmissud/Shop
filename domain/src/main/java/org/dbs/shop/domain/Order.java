package org.dbs.shop.domain;

import java.util.Date;
import java.util.List;

public class Order {

	private final int number;
	private final Date dateOrder;
	private Date dateShip;

	private float total;

	private List<Item> items;

	public Order(final int number, final Date dateOrder) {
		this.number = number;
		this.dateOrder = dateOrder;
	}

}

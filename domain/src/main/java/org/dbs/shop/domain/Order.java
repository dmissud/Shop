package org.dbs.shop.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order {

	private Customer customer;

	private final int number;

	private final LocalDate orderDate;

	private LocalDate shipmentDate;

	private BigDecimal total;

	private List<Item> items;

	public Order(final int number, final LocalDate orderDate) {
		this.number = number;
		this.orderDate = orderDate;
	}

}

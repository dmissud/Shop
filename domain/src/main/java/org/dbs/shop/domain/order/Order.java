package org.dbs.shop.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.dbs.shop.domain.Item;
import org.dbs.shop.domain.customer.Customer;

public class Order {

	@NotNull
	private final Customer customer;

	private final int number;

	private final LocalDate orderDate;

	private LocalDate shipmentDate;

	private BigDecimal total = BigDecimal.ZERO;

	private final List<Item> items;

	public Order(final Customer customer, final int number, final LocalDate orderDate) {
		this.customer = customer;
		this.number = number;
		this.orderDate = orderDate;
		items = new ArrayList<Item>();
	}

	public void addItem(final Item item) {
		items.add(item);
		total = total.add(item.getTotalCost());
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getNumber() {
		return number;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public LocalDate getShipmentDate() {
		return shipmentDate;
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}

}

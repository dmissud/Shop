package org.dbs.shop.exposition.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Order", description = "Order data")
public class OrderDTO {

	private int number;

	private LocalDate orderDate;

	private LocalDate shipmentDate;

	private BigDecimal total;

	private List<ItemDTO> items;

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(final LocalDate shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(final List<ItemDTO> items) {
		this.items = items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(final BigDecimal total) {
		this.total = total;
	}

}

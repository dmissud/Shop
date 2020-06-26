package org.dbs.shop.exposition.order;

import java.math.BigDecimal;

public class ItemDTO {

	private String description;

	private BigDecimal price;

	private int quantity;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

}

package org.dbs.shop.domain;

import java.math.BigDecimal;

public class Item {

	private final String productName;

	private final BigDecimal productPrice;

	private final int quantity;

	public Item(final String productName, final BigDecimal productPrice, final int quantity) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getTotalCost() {
		return productPrice.multiply(BigDecimal.valueOf(quantity));
	}

}

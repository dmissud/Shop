package org.dbs.shop.exposition.order;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Item", description = "Item data")
public class ItemDTO {

	private String description;

	private BigDecimal price;

	private int quantity;

	@ApiModelProperty(example = "Bananas", required = true, value = "Item description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@ApiModelProperty(example = "1.5", required = true, value = "Item price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	@ApiModelProperty(example = "2", required = true, value = "Item quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

}

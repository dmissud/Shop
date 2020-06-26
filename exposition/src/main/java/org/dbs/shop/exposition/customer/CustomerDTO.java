package org.dbs.shop.exposition.customer;

import javax.validation.constraints.NotNull;

public class CustomerDTO {

	@NotNull
	private String customerName;

	public CustomerDTO(final String customerName) {
		this.customerName = customerName;
	}

	public CustomerDTO() {
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}
}

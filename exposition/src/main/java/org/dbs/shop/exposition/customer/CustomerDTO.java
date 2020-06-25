package org.dbs.shop.exposition.customer;

public class CustomerDTO {
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

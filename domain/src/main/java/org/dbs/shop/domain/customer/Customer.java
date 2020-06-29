package org.dbs.shop.domain.customer;

public class Customer {

	private final String name;

	private final String password;

	public Customer(final String name, final String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}

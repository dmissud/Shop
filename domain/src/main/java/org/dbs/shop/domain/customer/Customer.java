package org.dbs.shop.domain.customer;

public class Customer {

	private Long id;

	private final String name;

	private final String password;

	public Customer(final String name, final String password) {
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}

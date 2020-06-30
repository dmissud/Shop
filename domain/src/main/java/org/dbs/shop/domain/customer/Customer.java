package org.dbs.shop.domain.customer;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private final String name;

	private final String password;

	private final Set<RoleTypeEnum> roles;

	public Customer(final String name, final String password) {
		this.name = name;
		this.password = password;
		roles = new HashSet<>();
		addRole(RoleTypeEnum.ROLE_USER);
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void addRole(final RoleTypeEnum role) {
		roles.add(role);
	}

	public Set<RoleTypeEnum> getRoles() {
		return roles;
	}

}

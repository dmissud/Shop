package org.dbs.shop.infrastructure.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.dbs.shop.domain.customer.RoleTypeEnum;

@Entity
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;

	private String password;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<RoleTypeEnum> roles = new HashSet<>();

	/**
	 * Default constructor needed by Hibernate
	 */
	public CustomerEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setRoles(final Set<RoleTypeEnum> roles) {
		this.roles = roles;
	}

	public Set<RoleTypeEnum> getRoles() {
		return roles;
	}
}

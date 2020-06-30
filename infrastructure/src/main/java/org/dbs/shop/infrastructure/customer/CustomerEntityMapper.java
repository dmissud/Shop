package org.dbs.shop.infrastructure.customer;

import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.customer.RoleTypeEnum;
import org.dbs.shop.infrastructure.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper extends AbstractMapper<Customer, CustomerEntity> {

	@Override
	public Customer mapToDomain(final CustomerEntity customerEntity) {
		final Customer customer = new Customer(customerEntity.getUserName(), customerEntity.getPassword());
		for (final RoleTypeEnum role : customerEntity.getRoles()) {
			customer.addRole(role);
		}
		return customer;
	}

	@Override
	public CustomerEntity mapToEntity(final Customer customer) {
		final CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setUserName(customer.getName());
		customerEntity.setPassword(customer.getPassword());
		for (final RoleTypeEnum role : customer.getRoles()) {
			customerEntity.getRoles().add(role);
		}
		return customerEntity;
	}

}
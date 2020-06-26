package org.dbs.shop.infrastructure.customer;

import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.infrastructure.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper extends AbstractMapper<Customer, CustomerEntity> {

	@Override
	public Customer mapToDomain(final CustomerEntity customerEntity) {
		final Customer customer = new Customer(customerEntity.getUserName(), customerEntity.getPassword());
		customer.setId(customerEntity.getId());
		return customer;
	}

	@Override
	public CustomerEntity mapToEntity(final Customer customer) {
		final CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customer.getId());
		customerEntity.setUserName(customer.getName());
		customerEntity.setPassword(customer.getPassword());
		return customerEntity;
	}

}
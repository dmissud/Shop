package org.dbs.shop.infrastructure;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAlreadyExistException;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.dbs.shop.domain.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryCustomerImpl implements IRepositoryCustomer {

	@Autowired
	private ICustomerJpaRepository customerJpaRepository;

	@Override
	public void save(final Customer customer) throws CustomerAlreadyExistException {

		CustomerEntity customerEntity = customerJpaRepository.findByUserName(customer.getName());
		if (customerEntity == null) {
			customerEntity = new CustomerEntity();
			customerEntity.setUserName(customer.getName());
			customerEntity.setPassword(customer.getPassword());
			customerJpaRepository.save(customerEntity);
		} else {
			throw new CustomerAlreadyExistException(customer.getName());
		}

	}

	@Override
	public Customer findByName(final String customerName) {
		final CustomerEntity customerEntity = customerJpaRepository.findByUserName(customerName);
		if (customerEntity != null) {
			return new Customer(customerEntity.getUserName(), customerEntity.getPassword());
		}
		throw new NotFoundException("A", "Customer not in repository" + customerName);
	}
}

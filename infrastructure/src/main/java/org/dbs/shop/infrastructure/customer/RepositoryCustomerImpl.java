package org.dbs.shop.infrastructure.customer;

import java.util.List;

import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.customer.IRepositoryCustomer;
import org.dbs.shop.domain.exception.CustomerAlreadyExistException;
import org.dbs.shop.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryCustomerImpl implements IRepositoryCustomer {

	@Autowired
	private ICustomerJpaRepository customerJpaRepository;

	@Autowired
	private CustomerEntityMapper mapper;

	@Override
	public void save(final Customer customer) {
		CustomerEntity customerEntity = customerJpaRepository.findByUserName(customer.getName());
		if (customerEntity == null) {
			customerEntity = mapper.mapToEntity(customer);
			customerJpaRepository.save(customerEntity);
		} else {
			throw new CustomerAlreadyExistException(customer.getName());
		}
	}

	@Override
	public Customer findByName(final String customerName) {
		final CustomerEntity customerEntity = customerJpaRepository.findByUserName(customerName);
		if (customerEntity != null) {
			return mapper.mapToDomain(customerEntity);
		}
		throw new NotFoundException("A", "Customer not in repository" + customerName);
	}

	@Override
	public List<Customer> listCustomers() {
		final List<CustomerEntity> customers = customerJpaRepository.findAll() ;
		return mapper.mapToDomainList(customers);
	}
}

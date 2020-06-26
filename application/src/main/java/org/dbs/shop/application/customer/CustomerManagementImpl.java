package org.dbs.shop.application.customer;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.customer.IRepositoryCustomer;
import org.dbs.shop.domain.exception.CustomerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManagementImpl implements ICustomerManagement {

	public static final String DEFAULTPASSWORD = "defaultpassword";

	@Autowired
	private IRepositoryCustomer repositoryCustomer;

	@Override
	public void referenceCustomer(final String customerName) throws CustomerAlreadyExistException {
		// Creer un customer
		RandomStringUtils.randomAlphanumeric(10);
		final Customer customer = new Customer(customerName, RandomStringUtils.randomAlphanumeric(10));

		// Reference un customer
		repositoryCustomer.save(customer);
	}

	@Override
	public Customer retrieveCustomerByName(final String customerName) {
		// Reference un customer

		return repositoryCustomer.findByName(customerName);
	}

	@Override
	public List<Customer> listCustomers() {
		return repositoryCustomer.listCustomers();
	}
}

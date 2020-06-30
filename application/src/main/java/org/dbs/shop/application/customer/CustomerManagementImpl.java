package org.dbs.shop.application.customer;

import java.util.List;

import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.customer.IRepositoryCustomer;
import org.dbs.shop.domain.customer.RoleTypeEnum;
import org.dbs.shop.domain.exception.CustomerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerManagementImpl implements ICustomerManagement {

	public static final String DEFAULT_PASSWORD = "change_me";

	@Autowired
	private IRepositoryCustomer repositoryCustomer;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void referenceCustomer(final String customerName) throws CustomerAlreadyExistException {
		// Creer un customer

		final Customer customer = new Customer(customerName,  passwordEncoder.encode(DEFAULT_PASSWORD));
		customer.addRole(RoleTypeEnum.ROLE_USER);
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

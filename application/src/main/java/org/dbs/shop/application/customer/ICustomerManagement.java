package org.dbs.shop.application.customer;

import java.util.List;

import org.dbs.shop.domain.customer.Customer;

public interface ICustomerManagement {

	void referenceCustomer(String customerName);

	Customer retrieveCustomerByName(String customerName);

	List<Customer> listCustomers();
}

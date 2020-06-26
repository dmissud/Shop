package org.dbs.shop.domain.customer;

import java.util.List;

import org.dbs.shop.domain.exception.CustomerAlreadyExistException;
import org.dbs.shop.domain.exception.NotFoundException;

public interface IRepositoryCustomer {

	void save(Customer customer) throws CustomerAlreadyExistException;

	Customer findByName(String customerName) throws NotFoundException;

	List<Customer> listCustomers();
}

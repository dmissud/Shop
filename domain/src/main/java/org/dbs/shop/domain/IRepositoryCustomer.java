package org.dbs.shop.domain;

public interface IRepositoryCustomer {

	void save(Customer customer) throws CustomerAlreadyExistException;

	Customer findByName(String customerName) throws NotFoundException;
}

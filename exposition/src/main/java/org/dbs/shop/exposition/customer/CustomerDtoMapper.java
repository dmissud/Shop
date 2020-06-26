package org.dbs.shop.exposition.customer;

import org.dbs.shop.common.AbstractMapper;
import org.dbs.shop.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper extends AbstractMapper<CustomerDTO, Customer> {

	@Override
	public CustomerDTO mapToDto(final Customer customer) {
		final CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerName(customer.getName());
		return customerDto;
	}

	@Override
	public Customer mapToDomain(final CustomerDTO customerDTO) {
		final Customer customer = new Customer(customerDTO.getCustomerName(), null);
		return customer;
	}

}
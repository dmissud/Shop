package org.dbs.shop.exposition.customer;

import org.dbs.shop.common.AbstractMapper;
import org.dbs.shop.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerFullDtoMapper extends AbstractMapper<CustomerFullDTO, Customer> {

    @Override
    public CustomerFullDTO mapToDto(final Customer customer) {
        final CustomerFullDTO customerFullDto = new CustomerFullDTO();
        customerFullDto.setCustomerName(customer.getName());
        customerFullDto.setPassword(customer.getPassword());
        return customerFullDto;
    }

    @Override
    public Customer mapToDomain(final CustomerFullDTO customerFullDTO) {
        return new Customer(customerFullDTO.getCustomerName(), customerFullDTO.getPassword());
    }

}
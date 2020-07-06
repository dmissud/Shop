package org.dbs.shop.exposition.customer;

import org.dbs.shop.common.AbstractMapper;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class CustomerFullDtoMapper extends AbstractMapper<CustomerFullDTO, Customer> {

    @Override
    public CustomerFullDTO mapToDto(final Customer customer) {
        final CustomerFullDTO customerFullDto = new CustomerFullDTO();
        customerFullDto.setCustomerName(customer.getUserName());
        customerFullDto.setPassword(customer.getPassword());
        return customerFullDto;
    }

}
package org.dbs.shop.infratructure.customer;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.infratructure.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper extends AbstractMapper<Customer, CustomerEntity> {
    @Override
    public Customer mapToDomain(CustomerEntity customerEntity) {
        return new Customer(customerEntity.getUserName(), customerEntity.getPassword());

    }

    @Override
    public CustomerEntity mapToEntity(Customer customer) {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUserName(customer.getName());
        customerEntity.setPassword(customer.getPassword());
        return customerEntity;
    }
}

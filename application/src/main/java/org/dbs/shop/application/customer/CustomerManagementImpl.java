package org.dbs.shop.application.customer;

import org.apache.commons.lang3.RandomStringUtils;
import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManagementImpl implements ICustomerManagement {

    @Autowired
    private IRepositoryCustomer repositoryCustomer;

    @Override
    public Customer referenceCustomer(String customerName){
        // Creer un customer
        Customer customer = new Customer(customerName, RandomStringUtils.randomAlphanumeric(10));

        // Reference un customer
        repositoryCustomer.save(customer);
        return customer;
    }

    @Override
    public Customer retrieveCustomerByName(String customerName) {
        // Reference un customer

        return repositoryCustomer.findByName(customerName);
    }

    @Override
    public List<Customer> retrieveAllCustomers() {
        return repositoryCustomer.retrieveAllCustomers();
    }
}

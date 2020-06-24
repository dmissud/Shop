package org.dbs.shop.application.customer;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAllReadyExistException;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManagementImpl implements ICustomerManagement {

    public static final String DEFAULTPASSWORD = "defaultpassword";

    @Autowired
    private IRepositoryCustomer repositoryCustomer;

    @Override
    public void referenceCustomer(String customerName) throws CustomerAllReadyExistException {
        // Creer un customer
        Customer customer = new Customer(customerName, DEFAULTPASSWORD);

        // Reference un customer
        repositoryCustomer.save(customer);
    }
}

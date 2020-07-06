package org.dbs.shop.application.customer;

import lombok.extern.slf4j.Slf4j;
import org.dbs.shop.domain.common.AllReadyExistException;
import org.dbs.shop.domain.common.NotFoundException;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.IRepositoryCustomer;
import org.dbs.shop.domain.user.IRepositoryUser;
import org.dbs.shop.domain.user.RoleType;
import org.dbs.shop.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class CustomerManagementImpl implements ICustomerManagement {

    @Autowired
    private IRepositoryUser repositoryUser;

    @Autowired
    private IRepositoryCustomer repositoryCustomer;

    @Override
    public Customer referenceCustomer(String customerName, String email) {
        User user;
        Customer customer;
        try {
            user = repositoryUser.findByName(customerName);
            if (user.haveRole(RoleType.ROLE_CUSTOMER)) {
                throw new AllReadyExistException("Name allready exist : " + customerName);
            }
        } catch (NotFoundException notFoundException) {
            // Create an user with Customer role
            user = new User(customerName, email, RoleType.ROLE_CUSTOMER);
            log.debug("Create a new User");
        }
        customer = new Customer(repositoryCustomer.giveNextCustomerNumber(), user);
        // Reference un customer
        repositoryCustomer.save(customer);
        log.debug("Create a new Customer");
        return customer;
    }

    @Override
    public Customer retrieveCustomerByName(String customerName) {
        return repositoryCustomer.findByName(customerName);
    }

    @Override
    public List<Customer> retrieveAllCustomers() {
        return repositoryCustomer.retrieveAllCustomers();
    }
}

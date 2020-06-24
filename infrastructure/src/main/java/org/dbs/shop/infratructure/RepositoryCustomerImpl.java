package org.dbs.shop.infratructure;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAllReadyExistException;
import org.dbs.shop.domain.CustomerNotFoundException;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryCustomerImpl implements IRepositoryCustomer {

    @Autowired
    ICustomerJpaRepository customerJpaRepository;

    @Override
    public void save(Customer customer) throws CustomerAllReadyExistException {

        CustomerEntity customerEntity = customerJpaRepository.findByUserName(customer.getName());
        if (customerEntity == null) {
            customerEntity = new CustomerEntity();
            customerEntity.setUserName(customer.getName());
            customerEntity.setPassword(customer.getPassword());
            customerJpaRepository.save(customerEntity);
        } else {
            throw new CustomerAllReadyExistException(customer.getName());
        }

    }

    @Override
    public Customer findByName(String customerName) throws CustomerNotFoundException {
        CustomerEntity customerEntity = customerJpaRepository.findByUserName(customerName);
        if (customerEntity != null) {
            return new Customer(customerEntity.getUserName(), customerEntity.getPassword());
        }
        throw new CustomerNotFoundException(customerName);
    }
}

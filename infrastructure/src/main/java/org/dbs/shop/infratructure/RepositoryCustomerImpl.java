package org.dbs.shop.infratructure;

import org.dbs.shop.domain.AllReadyExistException;
import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.dbs.shop.domain.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryCustomerImpl implements IRepositoryCustomer {

    @Autowired
    ICustomerJpaRepository customerJpaRepository;

    @Override
    public void save(Customer customer) {

        CustomerEntity customerEntity = customerJpaRepository.findByUserName(customer.getName());
        if (customerEntity == null) {
            customerEntity = new CustomerEntity();
            customerEntity.setUserName(customer.getName());
            customerEntity.setPassword(customer.getPassword());
            customerJpaRepository.save(customerEntity);
        } else {
            throw new AllReadyExistException("Customer All Ready Exist : " + customer.getName());
        }

    }

    @Override
    public Customer findByName(String customerName) {
        CustomerEntity customerEntity = customerJpaRepository.findByUserName(customerName);
        if (customerEntity != null) {
            return new Customer(customerEntity.getUserName(), customerEntity.getPassword());
        }
        throw new NotFoundException("Customer Not Found : " + customerName);
    }
}

package org.dbs.shop.infratructure.common.customer;

import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.IRepositoryCustomer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryCustomerImpl implements IRepositoryCustomer {
    @Override
    public int giveNextCustomerNumber() {
        return 0;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer findByNumber(int number) {
        return null;
    }

    @Override
    public Customer findByName(String customerName) {
        return null;
    }

    @Override
    public List<Customer> retrieveAllCustomers() {
        return null;
    }
}

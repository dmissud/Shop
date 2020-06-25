package org.dbs.shop.application.customer;

import org.dbs.shop.domain.CustomerAlreadyExistException;
import org.dbs.shop.domain.NotFoundException;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerManagementTest {

	@Autowired
	private ICustomerManagement customerManagement;

	@MockBean
	private IRepositoryCustomer customerRepository;

	@Test
	public void testReferenceCustomer() throws CustomerAlreadyExistException {
		customerManagement.referenceCustomer("john Doe");
	}

	@Test
	public void testRetrieveCustomerByName() throws NotFoundException {
		customerManagement.retrieveCustomerByName("john Doe");
	}

}

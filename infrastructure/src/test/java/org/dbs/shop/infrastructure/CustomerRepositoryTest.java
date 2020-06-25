package org.dbs.shop.infrastructure;

import static org.junit.Assert.assertNotNull;

import org.dbs.shop.SpringBootAppTest;
import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAlreadyExistException;
import org.dbs.shop.domain.NotFoundException;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootAppTest.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private IRepositoryCustomer customerRepository;

	@Before
	public void setUp() {
		// given
		final CustomerEntity customer = new CustomerEntity();
		customer.setUserName("john doe");
		customer.setPassword("password");
		testEntityManager.persist(customer);
	}

	@Test
	public void newCreatedCustomerShouldBeFound() throws CustomerAlreadyExistException, NotFoundException {
		final Customer customerCreated = customerRepository.findByName("john doe");
		assertNotNull(customerCreated);
	}

	@Test(expected = CustomerAlreadyExistException.class)
	public void createAnExistingCustomerShouldfail() throws CustomerAlreadyExistException, NotFoundException {
		final Customer customer = new Customer("john doe", "password");
		customerRepository.save(customer);
	}

}

package org.dbs.shop.infrastructure;

import static org.junit.Assert.assertNotNull;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.CustomerAlreadyExistException;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.dbs.shop.domain.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRespositoryTest {

	@Autowired
	private TestEntityManager entitymanager;

	@Autowired
	private IRepositoryCustomer repositoryCustomer;

	@Before
	public void init() {
		// Given for all tests
		final CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setUserName("john doe");
		customerEntity.setPassword("password");
		entitymanager.persist(customerEntity);
	}

	@Test
	public void alreadyExistCustomerShouldBeFound() {
		// When
		final Customer customer = repositoryCustomer.findByName("john doe");
		// Then
		assertNotNull(customer);
	}

	@Test(expected = NotFoundException.class) // then
	public void findUnknownCustomerShouldFail() {
		// When
		repositoryCustomer.findByName("jane doe");
	}

	@Test(expected = CustomerAlreadyExistException.class) // then
	public void createExistingCustomerShouldFail() throws CustomerAlreadyExistException {
		// Given for this test
		final Customer customer = new Customer("john doe", "password");
		// When
		repositoryCustomer.save(customer);
	}
}

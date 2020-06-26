package org.dbs.shop.infrastructure.customer;

import static org.junit.Assert.assertNotNull;

import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.customer.IRepositoryCustomer;
import org.dbs.shop.domain.exception.CustomerAlreadyExistException;
import org.dbs.shop.domain.exception.NotFoundException;
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
	public void already_Exist_Customer_Should_Be_Found() {
		// When
		final Customer customer = repositoryCustomer.findByName("john doe");
		// Then
		assertNotNull(customer);
	}

	@Test(expected = NotFoundException.class) // then
	public void find_Unknown_Customer_Should_Fail() {
		// When
		repositoryCustomer.findByName("jane doe");
	}

	@Test(expected = CustomerAlreadyExistException.class) // then
	public void create_Existing_Customer_Should_Fail() {
		// Given for this test
		final Customer customer = new Customer("john doe", "password");
		// When
		repositoryCustomer.save(customer);
	}
}

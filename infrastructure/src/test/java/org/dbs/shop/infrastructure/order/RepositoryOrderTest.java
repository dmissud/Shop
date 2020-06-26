package org.dbs.shop.infrastructure.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.dbs.shop.domain.Item;
import org.dbs.shop.domain.customer.Customer;
import org.dbs.shop.domain.order.IRepositoryOrder;
import org.dbs.shop.domain.order.Order;
import org.dbs.shop.infrastructure.customer.CustomerEntity;
import org.dbs.shop.infrastructure.customer.CustomerEntityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryOrderTest {

	private static final int ORDER_NUMBER = 2;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IRepositoryOrder repositoryOrder;

	@Autowired
	private CustomerEntityMapper mapper;

	@Test
	public void Save_order_with_Existing_Customer_Should_success() {
		// Given

		final CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setUserName("john doe");
		customerEntity.setPassword("password");
		entityManager.persistAndFlush(customerEntity);

		final Customer customer = mapper.mapToDomain(customerEntity);

		final Order order = new Order(customer, 2, LocalDate.now());
		order.addItem(new Item("Banana", BigDecimal.valueOf(1.5), 10));
		order.addItem(new Item("Pinaple", BigDecimal.valueOf(5.99), 1));
		order.addItem(new Item("Mango", BigDecimal.valueOf(3.01), 2));

		// When
		repositoryOrder.save(order);

		// Then
		final List<?> ordersFound = entityManager.getEntityManager()
				.createQuery("select o from OrderEntity o where o.number = :orderNumber")
				.setParameter("orderNumber", ORDER_NUMBER).getResultList();
		assertThat(ordersFound.size()).isEqualTo(1);
	}

}

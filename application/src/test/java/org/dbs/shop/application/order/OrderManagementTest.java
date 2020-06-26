package org.dbs.shop.application.order;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dbs.shop.domain.Customer;
import org.dbs.shop.domain.IRepositoryCustomer;
import org.dbs.shop.domain.Item;
import org.dbs.shop.domain.Order;
import org.dbs.shop.domain.order.IRepositoryOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { OrderManagementImpl.class })
public class OrderManagementTest {

	private static final String CUSTOMER_NAME = "John Doe";

	@Autowired
	private IOrderManagement orderManagement;

	@MockBean
	private IRepositoryCustomer customerRepository;

	@MockBean
	private IRepositoryOrder orderRepository;

	@Test
	public void create_Order_Should_Success() {
		// Given
		when(customerRepository.findByName(any(String.class))).thenReturn(new Customer(CUSTOMER_NAME, "password"));

		final List<Item> items = new ArrayList<>();
		items.add(new Item("Banana", BigDecimal.valueOf(1.5), 10));
		items.add(new Item("Pinaple", BigDecimal.valueOf(5.99), 1));
		items.add(new Item("Mango", BigDecimal.valueOf(3.01), 2));

		// When
		final Order order = orderManagement.createOrder(CUSTOMER_NAME, items);

		// Then
		Mockito.verify(customerRepository, Mockito.times(1)).findByName(CUSTOMER_NAME);
		Mockito.verify(orderRepository, Mockito.times(1)).save(any(Order.class));
	}

}

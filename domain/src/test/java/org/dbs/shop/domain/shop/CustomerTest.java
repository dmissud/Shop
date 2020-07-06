package org.dbs.shop.domain.shop;

import org.apache.commons.lang3.RandomUtils;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.Item;
import org.dbs.shop.domain.shop.Order;
import org.dbs.shop.domain.user.RoleType;
import org.dbs.shop.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Validation of the Customer logic")
class CustomerTest {

    private static final String CUSTOMER_NAME = "Erold Flyn";
    private static final String CUSTOMER_PASSWORD = "IUFudsjazjuy765";
    private static final String CUSTOMER_EMAIL = "erold.flyn@gmail.com";
    private Order order;

    @BeforeEach
    void initSingleTest() {
        List<Item> items = new LinkedList<>();
        items.add(new Item("Produit1", BigDecimal.valueOf(1),1));
        order = new Order(RandomUtils.nextInt(1000, 10000000),
                LocalDate.now(), items);
    }

    @Test
    @DisplayName("Add a new command to a customer")
    void should_have_a_command_in_customer_when_i_add_one() {
        // Given
        User user = new User(CUSTOMER_NAME, CUSTOMER_EMAIL, RoleType.ROLE_CUSTOMER);
        Customer customer = new Customer(100, user);

        // When
        int nbCmd = customer.numberOfOrders();
        customer.placeOrder(order);

        // Then
        assertThat(customer.numberOfOrders()).isEqualTo(nbCmd+1);
        assertThat(customer.retrieveOrder(order.getNumber())).isEqualTo(order);
        assertThat(order.getCustomer()).isEqualTo(customer);
    }
}

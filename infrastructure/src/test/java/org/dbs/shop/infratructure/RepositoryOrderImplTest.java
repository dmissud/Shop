package org.dbs.shop.infratructure;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.IRepositoryOrder;
import org.dbs.shop.domain.shop.Item;
import org.dbs.shop.domain.shop.Order;
import org.dbs.shop.domain.user.RoleType;
import org.dbs.shop.domain.user.User;
import org.dbs.shop.infratructure.order.RepositoryOrderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("Validation of the repository of Orders")
class RepositoryOrderImplTest {

    private static final String CUSTOMER_NAME = "Jhon Lennon";
    private static final String CUSTOMER_EMAIL = "bigPassword@email.glo";
    @Autowired
    IRepositoryOrder repositoryOrder;

    @Autowired
    private TestEntityManager entityManager;
    private Customer customer;
    private User user;
    private Order order;

    @BeforeEach
    void init() {
        List<Item> items = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < 10; i++) {
            Item item = new Item(RandomStringUtils.randomAlphanumeric(10),
                    new BigDecimal(RandomUtils.nextInt(4, 150)),
                    RandomUtils.nextInt(1, 15));
            total = total.add(item.getCost());
            items.add(item);
        }

        // When
        order = new Order(RandomUtils.nextInt(1005, 10000000),
                LocalDate.now(), items);

        user = new User(CUSTOMER_NAME, CUSTOMER_EMAIL, RoleType.ROLE_CUSTOMER);
        customer = new Customer(23, user);
    }

    @Test
    @DisplayName("Test of the correct increment of the number of order")
    void should_number_of_command_correctly_increment() {
        // Given

        // When
        int lastNumberOfOrder = repositoryOrder.giveNextOrderNumber();

        // Then
        assertThat(lastNumberOfOrder).isEqualTo(RepositoryOrderImpl.FIST_NUMBER_OF_ORDER);
    }

    @Test
    @DisplayName("Order creation ok")
    void should_create_order_correctly_realized() {
        // Given
        order.setCustomer(customer);

        // When
        repositoryOrder.save(order);

        // Then
        List lstCust = entityManager.getEntityManager().createQuery("select o from OrderEntity o where o.number= :numberAttenduDansQuery")
                .setParameter("numberAttenduDansQuery", order.getNumber())
                .getResultList();
        assertThat(lstCust.size()).isEqualTo(1);
    }
}
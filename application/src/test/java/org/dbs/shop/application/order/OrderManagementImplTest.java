package org.dbs.shop.application.order;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.dbs.shop.application.order.IOrderCreateUseCase.PlaceItemCommand;
import org.dbs.shop.application.order.IOrderCreateUseCase.PlaceOrderCommand;
import org.dbs.shop.domain.common.NotFoundException;
import org.dbs.shop.domain.shop.Customer;
import org.dbs.shop.domain.shop.IRepositoryCustomer;
import org.dbs.shop.domain.shop.IRepositoryOrder;
import org.dbs.shop.domain.shop.Order;
import org.dbs.shop.domain.user.IRepositoryUser;
import org.dbs.shop.domain.user.RoleType;
import org.dbs.shop.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderManagementImpl.class})
class OrderManagementImplTest {
    private static final String CUSTOMER_NAME = "John Travolta";
    private static final String CUSTOMER_PASSWORD = "ABigPassword";
    private static final String CUSTOMER_NAME_NOT_EXIST = "j'existe pas";

    @Autowired
    IOrderCreateUseCase orderCreateUseCase;

    @MockBean
    IRepositoryCustomer repositoryCustomer;

    @MockBean
    IRepositoryOrder repositoryOrder;

    private Customer customer;


    @BeforeEach
    void setUp() {
        User user = new User(CUSTOMER_NAME, CUSTOMER_PASSWORD, RoleType.ROLE_CUSTOMER);
        customer = new Customer(23, user);
    }


    @Test
    @DisplayName("Create of a order for a exist customer")
    void should_i_create_correct_order_when_customer_exist() {
        // Given
        when(repositoryCustomer.findByName(CUSTOMER_NAME)).thenReturn(customer);
        when(repositoryOrder.giveNextOrderNumber()).thenReturn(12345678);

        List<PlaceItemCommand> placeItemCommands = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            PlaceItemCommand itemCmd = new PlaceItemCommand(RandomStringUtils.randomAlphanumeric(10),
                    RandomUtils.nextInt(4, 150),
                    RandomUtils.nextInt(1, 15));
            placeItemCommands.add(itemCmd);
        }
        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(CUSTOMER_NAME, placeItemCommands);

        // When
        Order order = orderCreateUseCase.placeOrder(placeOrderCommand);

        // Then
        assertThat(order).isNotNull();
        assertThat(order.getCustomer().getName()).isEqualTo(CUSTOMER_NAME);
        assertThat(order.getItemCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("Can't create of a order for a non exist customer")
    void should_i_cant_create_order_when_customer_not_exist() {
        // Given
        when(repositoryCustomer.findByName(CUSTOMER_NAME_NOT_EXIST)).thenThrow(new NotFoundException("Customer Not Found : "+ CUSTOMER_NAME_NOT_EXIST));
        when(repositoryOrder.giveNextOrderNumber()).thenReturn(12345678);

        List<PlaceItemCommand> placeItemCommands = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            PlaceItemCommand itemCmd = new PlaceItemCommand(RandomStringUtils.randomAlphanumeric(10),
                    RandomUtils.nextInt(4, 150),
                    RandomUtils.nextInt(1, 15));
            placeItemCommands.add(itemCmd);
        }
        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(CUSTOMER_NAME_NOT_EXIST, placeItemCommands);

        // When
        Throwable thrown = catchThrowable(() -> {
            Order order = orderCreateUseCase.placeOrder(placeOrderCommand);
        });

        // Then
        assertThat(thrown).isNotNull();
        assertThat(thrown).as("Try find a non existing customer").hasMessage("Customer Not Found : "+ CUSTOMER_NAME_NOT_EXIST);
    }
}